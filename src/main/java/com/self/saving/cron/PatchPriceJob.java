package com.self.saving.cron;

import com.self.saving.model.CryptoCurrenciesModel;
import com.self.saving.model.EmaModel;
import com.self.saving.model.enums.PeriodEnum;
import com.self.saving.model.validators.EmaValidator;
import com.self.saving.service.CryptoCurrenciesService;
import com.self.saving.utillies.Utility;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//@Configuration
//@EnableScheduling
//@EnableAsync
@AllArgsConstructor
@Service
public class PatchPriceJob {

    private final CryptoCurrenciesService cryptoCurrenciesService;
    private final Validator validator;


//    @Scheduled(cron = "0 0 0 ? * * *")
//    public void patchCoinsLately() {
//        cryptoCurrenciesService.saveAllCoins(cryptoCurrenciesService.toCryptoCurrenciesInfoEntities(cryptoCurrenciesService.getProducts()));
//    }

    //    @Scheduled(cron = "0 0 0 ? * * *")
    public List<CryptoCurrenciesModel> patchEma() {

        var coins = cryptoCurrenciesService.getCoins();
        var executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<CompletableFuture<Void>> futures = coins.stream()
                .flatMap(coin -> Stream.of(
                        CompletableFuture.runAsync(() -> findEMA(coin, 50), executor),
                        CompletableFuture.runAsync(() -> findEMA(coin, 100), executor),
                        CompletableFuture.runAsync(() -> findEMA(coin, 200), executor)
                ))
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .exceptionally(ex -> null)
                .thenAccept(ac -> cryptoCurrenciesService.saveAllCoins(coins))
                .join();


        return coins.stream().filter(value-> !validator.validate(value.getEma(), EmaValidator.class).isEmpty() ).toList();
    }



    private void findEMA(CryptoCurrenciesModel cryptoCurrenciesModel,Integer days) {

        var start = String.valueOf(Utility.localDateTimeToTimestamp(LocalDateTime.now().minusDays(days+1)));
        var end = String.valueOf(Utility.localDateTimeToTimestamp(LocalDateTime.now()));


        var priceHistories = cryptoCurrenciesService.listCoins(cryptoCurrenciesModel.getId(), PeriodEnum.hour.getGranularity(), start, end);
        cryptoCurrenciesModel.setPriceHistories(priceHistories);

        var multiplier = 2.0 / (days + 1);
        var ema = priceHistories.subList(1,priceHistories.size()).stream().mapToDouble(value -> value.getClosingPrice().doubleValue()).average().orElse(0.0);


        var closingPrice = priceHistories.get(0).getClosingPrice().doubleValue();
        ema = (closingPrice * multiplier) + (ema * (1 - multiplier));

        var emaModel = cryptoCurrenciesModel.getEma();
        saveFlextableEmaDay(emaModel,String.valueOf(days),BigDecimal.valueOf(ema));
        cryptoCurrenciesModel.setEma(emaModel);


    }

    private void saveFlextableEmaDay(EmaModel emaModel, String attribute,BigDecimal value)  {
        try {
            var methodName = String.format("set%s%s", "Ema", attribute);
            var method = EmaModel.class.getMethod(methodName, BigDecimal.class);
            method.invoke(emaModel, value);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }




}
