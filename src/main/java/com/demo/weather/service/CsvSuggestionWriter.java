package com.demo.weather.service;

import com.demo.weather.dto.CsvSuggestionDto;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@Slf4j
@Component
public class CsvSuggestionWriter {
    private CsvMapper csvMapper = new CsvMapper();
    private CsvSchema schema = csvMapper
            .schemaFor(CsvSuggestionDto.class)
            .withHeader()
            .sortedBy("lon", "lat", "temp", "pressure", "humidity", "base");

    public void write(@NonNull String fileName, @NonNull CsvSuggestionDto data) {
        try {
            @Cleanup Writer writer = new PrintWriter(new FileWriter(fileName));
            doWrite(writer, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doWrite(@NonNull Writer writer, @NonNull CsvSuggestionDto data) throws IOException {
        log.info("writing data");
        csvMapper.writer().with(schema).writeValues(writer).write(data);
    }
}
