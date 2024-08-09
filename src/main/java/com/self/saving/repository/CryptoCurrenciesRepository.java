package com.self.saving.repository;

import com.self.saving.entity.CryptoCurrenciesInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CryptoCurrenciesRepository extends JpaRepository<CryptoCurrenciesInfoEntity, String> {


}
