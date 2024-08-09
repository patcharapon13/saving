package com.self.saving.controller;

import com.self.saving.cron.PatchPriceJob;
import com.self.saving.model.CryptoProductModel;
import com.self.saving.model.PriceHistories;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.self.saving.service.CryptoCurrenciesService;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/CryptoCurrencies")
@AllArgsConstructor
public class CryptoCurrenciesController {


    private CryptoCurrenciesService cryptoCurrenciesService;
    private final PatchPriceJob patchPriceJob;

    @GetMapping
    public ResponseEntity<List<PriceHistories>> get() {
        return ResponseEntity.ok(cryptoCurrenciesService.listCoins("OP-USDT","86400","1721203712000","1721309892"));
    }

    @GetMapping("/products")
    public ResponseEntity<Set<CryptoProductModel>> getProducts() {
        return ResponseEntity.ok(cryptoCurrenciesService.getProducts());


    }

    @GetMapping("/ema/{day}")
    public ResponseEntity<?> getPriceOverEma(@PathVariable Integer day) {
        return ResponseEntity.ok(cryptoCurrenciesService.listProductsBeingOverEma(Objects.requireNonNullElse(day,50)));
    }

    @GetMapping("/save")
    public ResponseEntity<?> saveProducts() {
        return ResponseEntity.ok(patchPriceJob.patchEma());
    }

//    @GetMapping("/save")
//    public ResponseEntity<?> saveProducts() {
//        cryptoCurrenciesService.saveProducts();
//        return ResponseEntity.ok("");
//    }
}
