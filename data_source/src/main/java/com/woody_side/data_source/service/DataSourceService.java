package com.woody_side.data_source.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class DataSourceService {

    private static final String COUNTRIES_FILE_NAME = "countries.json";

    @SneakyThrows
    public String loadCountriesFromFile() {
        //imitating service delays
//        Thread.sleep(3000);
        return Files.readString(Paths.get(Objects.requireNonNull(getClass().getClassLoader()
                .getResource(COUNTRIES_FILE_NAME))
                .toURI()));
    }
}
