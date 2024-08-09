package com.self.saving.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.self.saving.model.validators.EmaValidator;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.transform.Source;
import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmaModel {

    private String productId;

    @Null(groups = EmaValidator.class)
    private BigDecimal ema50;
    @Null(groups = EmaValidator.class)
    private BigDecimal ema100;
    @Null(groups = EmaValidator.class)
    private BigDecimal ema200;


}
