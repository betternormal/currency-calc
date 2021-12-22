package com.example.currencycalc.service;

import com.example.currencycalc.dto.CurrencyListDto;
import com.example.currencycalc.dto.CurrencyRequestDto;
import com.example.currencycalc.dto.CurrencyResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@Service("currencyService")
@RequiredArgsConstructor
public class CurrencyService {

    public CurrencyListDto getCurrency() throws Exception {

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);

        RestTemplate restTemplate = new RestTemplate(factory);
        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);

        String url = "http://api.currencylayer.com/live";
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url+"?"+"access_key=").build();
        ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
        HashMap<String, Object> currencyMap = (HashMap<String, Object>) resultMap.getBody().get("quotes");

        // 금액을 형식에 맞게 포매팅한다
        DecimalFormat df = new DecimalFormat("#,###.00");
        BigDecimal krwCurrency = new BigDecimal((double) currencyMap.get("USDKRW"));
        BigDecimal jpyCurrency = new BigDecimal((double) currencyMap.get("USDJPY"));
        BigDecimal phpCurrency = new BigDecimal((double) currencyMap.get("USDPHP"));

        String krwCurrencyStr = df.format(krwCurrency).toString();
        String jpyCurrencyStr = df.format(jpyCurrency).toString();
        String phpCurrencyStr = df.format(phpCurrency).toString();

        return new CurrencyListDto(krwCurrencyStr, jpyCurrencyStr, phpCurrencyStr);

    }
    public CurrencyResponseDto calcCurrency(CurrencyRequestDto currencyRequestDto) throws Exception {

        // 결과값 초기화
        String result = "";

        // request에서 금액과 국가를 꺼내 변수에 담는다
        BigDecimal amount = currencyRequestDto.getAmount();
        String country = currencyRequestDto.getCountry().toUpperCase();

        BigDecimal zero = BigDecimal.valueOf(0);
        BigDecimal tenThousand = BigDecimal.valueOf(10000);
        if(zero.compareTo(amount) == 1 || amount.compareTo(tenThousand) == 1){
            throw new Exception("금액은 0부터 10000까지 입력가능합니다");
        }

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);

        RestTemplate restTemplate = new RestTemplate(factory);
        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);

        String url = "http://api.currencylayer.com/live";
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url+"?"+"access_key=").build();
        ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
        HashMap<String, Object> currencyMap = (HashMap<String, Object>) resultMap.getBody().get("quotes");

        // double형은 정확한 계산에 불리하기 때문에 BigDecimal로 형변환후 금액을 곱한다
        double currencyDouble = (double) currencyMap.get("USD"+country);
        BigDecimal currency = new BigDecimal(currencyDouble);
        BigDecimal decimalResult = currency.multiply(amount);

        // 금액을 형식에 맞게 포매팅한다
        DecimalFormat df = new DecimalFormat("#,###.00");
        result = df.format(decimalResult).toString();

        return new CurrencyResponseDto(country, amount, result);

    }
}
