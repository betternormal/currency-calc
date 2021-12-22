package com.example.currencycalc.dto;

import java.math.BigDecimal;
import java.util.HashMap;

public class CurrencyResponseDto {
    private String country;
    private BigDecimal amount;
    private BigDecimal result;

    public CurrencyResponseDto(String country, BigDecimal amount, BigDecimal result) {
        this.country = country;
        this.amount = amount;
        this.result = result;
    }
}
