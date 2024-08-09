package com.self.saving.repository;

import com.self.saving.entity.CryptoCurrenciesInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoCurrenciesRepository extends JpaRepository<CryptoCurrenciesInfoEntity, String> {
}
