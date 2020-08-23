package com.demo.weather.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
        private Coordinate coord;//: { "lon": 139,"lat": 35},
        private String base;
        private MainObj main;
}
