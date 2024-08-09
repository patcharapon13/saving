package com.self.saving.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PeriodEnum {
    fiveMin("300"),hour("86400");

    @Getter
    private final String granularity;
}
