package com.example.currencycalc.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CurrencyRequestDto {
    private String country;
    private String amount;
}
