package com.example.currencycalc.controller;

import com.example.currencycalc.dto.CurrencyRequestDto;
import com.example.currencycalc.dto.CurrencyResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {
    @PostMapping("/currency")
    public CurrencyResponseDto getCurrency (CurrencyRequestDto currencyRequestDto) {
        
        BigDecimal amount = currencyRequestDto.getAmount();
        String country = currencyRequestDto.getCountry().toUpperCase();
        BigDecimal resultt = null;
        HashMap<String, Object> result = new HashMap<String, Object>();
        String jsonResult = "";

        try {
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000); //타임아웃 설정 5초
            factory.setReadTimeout(5000);//타임아웃 설정 5초

            RestTemplate restTemplate = new RestTemplate(factory);
            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);

            String url = "http://api.currencylayer.com/live";
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url+"?"+"access_key=bd5d5e0e77c92725758edd4be856c2d4").build();

            //이 한줄의 코드로 API를 호출해 MAP타입으로 전달 받는다.
            ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인

            HashMap<String, Object> result2 = (HashMap<String, Object>) resultMap.getBody().get("quotes");
            double currencyDouble = (double) result2.get("USD"+country);
            BigDecimal currency = new BigDecimal(currencyDouble);
            //Double currency = resultMap.getBody().get("quotes").get("USD"+country);

            resultt = currency.multiply(amount).setScale(2, RoundingMode.HALF_UP);
            //데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌
            ObjectMapper mapper = new ObjectMapper();
            jsonResult = mapper.writeValueAsString(resultMap.getBody());

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            System.out.println("dfdfdfdf");
            System.out.println(e.toString());

        } catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body"  , "excpetion오류");
            System.out.println(e.toString());
        }

        //return jsonResult;
        return new CurrencyResponseDto(country, amount, resultt);

    }

        //return ResponseEntity.ok(request.getRemoteAddr());
    }


