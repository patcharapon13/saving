package com.self.saving.service;

import com.self.saving.client.CoinBaseApi;
import com.self.saving.entity.CryptoCurrenciesInfoEntity;
import com.self.saving.mapper.CryptoCurrenciesMapper;
import com.self.saving.mapper.PriceHistoryMapper;
import com.self.saving.model.CryptoCurrenciesModel;
import com.self.saving.model.CryptoProductModel;
import com.self.saving.model.PriceHistories;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.self.saving.repository.CryptoCurrenciesRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CryptoCurrenciesService {

    private final CryptoCurrenciesRepository cryptoCurrenciesRepository;
    private final CoinBaseApi coinBaseApi;
    private final PriceHistoryMapper priceHistoryMapper;
    private final CryptoCurrenciesMapper cryptoCurrenciesMapper;


    public List<PriceHistories> listCoins(String id, String granularity, String start, String end) {
        return priceHistoryMapper.toPriceHistoriesList(coinBaseApi.getCoinsHistories(id, granularity, start, end));
    }

    public Set<CryptoProductModel> getProducts() {
        return coinBaseApi.getModels().parallelStream().flatMap(this::mapStatus).collect(Collectors.toSet());
    }

    private Stream<CryptoProductModel> mapStatus(CryptoProductModel cryptoProductModel) {
        try {
            var status = coinBaseApi.getStatus(cryptoProductModel.getId());
            cryptoProductModel.setPriceStatus(status);
            return Stream.of(cryptoProductModel);
        } catch (Exception e) {
            return Stream.empty();
        }
    }

//    public void saveProducts() {
//        saveCoins(CryptoCurrenciesInfoEntity.builder().productId("XRP-USDT").ema(EmaEntity.builder().productId("XRP-USDT").build()).price(BigDecimal.TEN).build());
//    }


    public CryptoCurrenciesInfoEntity saveCoin(CryptoCurrenciesModel entities) {
        return cryptoCurrenciesRepository.saveAndFlush(cryptoCurrenciesMapper.toCryptoCurrenciesInfoEntity(entities));
    }

    public List<CryptoCurrenciesInfoEntity> saveAllCoins(List<CryptoCurrenciesModel> models) {
        return cryptoCurrenciesRepository.saveAllAndFlush(cryptoCurrenciesMapper.toCryptoCurrenciesInfoEntities(models));
    }

    @Transactional(readOnly = true)
    public List<CryptoCurrenciesModel> getCoins() {
        return cryptoCurrenciesMapper.toCryptoCurrenciesModels(cryptoCurrenciesRepository.findAll());

    }

    public List<CryptoCurrenciesInfoEntity> toCryptoCurrenciesInfoEntities(List<CryptoCurrenciesModel> cryptoProductModel) {
        return cryptoCurrenciesMapper.toCryptoCurrenciesInfoEntities(cryptoProductModel);
    }


}
