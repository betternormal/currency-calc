package com.example.currencycalc.dto;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
@Getter
@Data
public class CurrencyResponseDto {
    private String country;
    private BigDecimal amount;
    private String result;

    public CurrencyResponseDto(String country, BigDecimal amount, String result) {
        this.country = country;
        this.amount = amount;
        this.result = result;
    }
}
