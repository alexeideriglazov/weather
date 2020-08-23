package com.demo.weather.service;

import com.demo.weather.domain.Weather;
import com.google.common.collect.ImmutableMap;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class WeatherApiClient {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${app.cityRequest}")
    private String suggestionUrl;

    public Weather findSuggestionsByCity(@NonNull String cityName) {
        log.info("sending api request");
        ResponseEntity<Weather> response = restTemplate.getForEntity(suggestionUrl, Weather.class, ImmutableMap.of("city", cityName));
        if(response.getStatusCode().isError())
            throw new RuntimeException();
        log.info("return api response");
        return response.getBody();

    }
}
