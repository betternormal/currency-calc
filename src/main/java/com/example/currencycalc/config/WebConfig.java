package com.example.currencycalc.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**").allowCredentials(true).allowedOrigins("http://localhost:3000");
//        registry.addMapping("/api/**").allowCredentials(true).allowedOrigins("http://127.0.0.1:5500/");
//
//    }
}
