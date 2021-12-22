package com.example.currencycalc.dto;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@Getter
public class CurrencyRequestDto {
    private String country;
    private BigDecimal amount;
}
