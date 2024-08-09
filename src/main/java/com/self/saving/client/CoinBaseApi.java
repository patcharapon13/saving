package com.self.saving.client;

import com.self.saving.model.CryptoProductModel;
import com.self.saving.model.PriceStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@FeignClient(name = "CoinBase", url = "${application.coinbase-endpoint.main}")
public interface CoinBaseApi {

    @GetMapping("/{id}/${application.coinbase-endpoint.histories}")
    Object[][] getCoinsHistories(@PathVariable String id
            , @RequestParam("granularity") String granularity
            , @RequestParam("start") String start
            , @RequestParam("end") String end);


    @GetMapping()
    Set<CryptoProductModel> getModels();

    @GetMapping("/{id}/${application.coinbase-endpoint.status}")
    PriceStatus getStatus(@PathVariable String id);
}
