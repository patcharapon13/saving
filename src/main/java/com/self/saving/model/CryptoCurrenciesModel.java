package com.self.saving.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CryptoCurrenciesModel {

    private String id;
    private BigDecimal price;
    private EmaModel ema;

    @ToString.Exclude
    @JsonIgnore
    private List<PriceHistories> priceHistories;

    
}
