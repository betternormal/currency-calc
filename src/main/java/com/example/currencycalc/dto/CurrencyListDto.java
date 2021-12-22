package com.example.currencycalc.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class CurrencyListDto {
    private String krw;
    private String jpy;
    private String php;

    public CurrencyListDto(String krwCurrencyStr, String jpyCurrencyStr, String phpCurrencyStr) {
        this.krw = krwCurrencyStr;
        this.jpy = jpyCurrencyStr;
        this.php = phpCurrencyStr;
    }
}
