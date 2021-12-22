package com.example.currencycalc.controller;

import com.example.currencycalc.dto.CurrencyRequestDto;
import com.example.currencycalc.common.DefaultRes;
import com.example.currencycalc.common.ResponseMessage;
import com.example.currencycalc.common.StatusCode;
import com.example.currencycalc.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainController {
    private final CurrencyService currencyService;

    @GetMapping ("/currency")
    public ResponseEntity getCurrency () throws Exception {

        return ResponseEntity.ok(
                DefaultRes.builder()
                        .data(currencyService.getCurrency())
                        .statusCode(StatusCode.OK)
                        .responseMessage(ResponseMessage.GET_SUCCESS)
                        .build()
        );
    }

    @PostMapping("/currency")
    public ResponseEntity calcCurrency (CurrencyRequestDto currencyRequestDto) throws Exception {

        return ResponseEntity.ok(
                DefaultRes.builder()
                        .data(currencyService.calcCurrency(currencyRequestDto))
                        .statusCode(StatusCode.OK)
                        .responseMessage(ResponseMessage.GET_SUCCESS)
                        .build()
        );
    }
}


