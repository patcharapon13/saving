package com.self.saving;

import com.self.saving.entity.CryptoCurrenciesInfoEntity;
import com.self.saving.entity.EmaEntity;
import com.self.saving.repository.CryptoCurrenciesRepository;
import com.self.saving.service.CryptoCurrenciesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CryptoCurrenciesServiceTest {

    @Mock
    private CryptoCurrenciesRepository cryptoCurrenciesRepository;

    @InjectMocks
    private CryptoCurrenciesService cryptoCurrenciesService;

    @Captor
    ArgumentCaptor<CryptoCurrenciesInfoEntity> captor;
//
//    void givenCoinsEntity_whenEntityIsSaved_thenSuccess(){
//        var entity = CryptoCurrenciesInfoEntity.builder()
//                .productId("XRP-USDT")
//                .price(BigDecimal.TEN)
////                .ema(new EmaEntity())
//                .createdDate(LocalDateTime.now())
//                .updatedDate(LocalDateTime.now())
//                .build();
//
//        cryptoCurrenciesService.saveCoin(entity);
//
//
//    }
}
