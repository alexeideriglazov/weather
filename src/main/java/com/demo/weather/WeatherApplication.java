package com.demo.weather;

import com.demo.weather.dto.CsvSuggestionDto;
import com.demo.weather.service.CsvSuggestionConverter;
import com.demo.weather.service.CsvSuggestionWriter;
import com.demo.weather.service.WeatherApiClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
public class WeatherApplication implements CommandLineRunner {
    @Autowired
    private CsvSuggestionWriter csvSuggestionWriter;
    @Autowired
    private WeatherApiClient weatherApiClient;
    @Autowired
    private CsvSuggestionConverter csvSuggestionConverter;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()));
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(WeatherApplication.class)
                .bannerMode(Banner.Mode.CONSOLE)
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        for(String city : args) {
            writeCityWeather(city.trim());
        }
        return;
    }

    public void writeCityWeather(String cityName) {
        String fileName = cityName + ".csv";
        CsvSuggestionDto data = csvSuggestionConverter
                .toCsvSuggestionDto(weatherApiClient.findSuggestionsByCity(cityName));
        csvSuggestionWriter.write(fileName, data);
        log.info("done for {}", cityName);
    }
}
