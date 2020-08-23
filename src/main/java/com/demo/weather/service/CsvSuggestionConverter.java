package com.demo.weather.service;

import com.demo.weather.domain.Weather;
import com.demo.weather.dto.CsvSuggestionDto;
import org.springframework.stereotype.Component;

@Component
public class CsvSuggestionConverter {

    public CsvSuggestionDto toCsvSuggestionDto(Weather weather) {
        CsvSuggestionDto dto = new CsvSuggestionDto();
        dto.setBase(weather.getBase());
        dto.setLat(weather.getCoord().getLat());
        dto.setLon(weather.getCoord().getLon());
        dto.setTemp(weather.getMain().getTemp());
        dto.setHumidity(weather.getMain().getHumidity());
        dto.setPressure(weather.getMain().getPressure());
        return dto;
    }
}
