package com.self.saving.entity.keys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCurrenciesInfoKeysEntity implements Serializable {
    private UUID cryptoId;
    private String productId;

}
