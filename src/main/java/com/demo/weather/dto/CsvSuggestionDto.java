package com.demo.weather.dto;

import lombok.Data;

@Data
public class CsvSuggestionDto {
    private double lon;
    private double lat;
    private double temp;
    private double pressure;
    private double humidity;
    private String base;
}
