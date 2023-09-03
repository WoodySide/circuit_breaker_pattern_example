package com.woody_side.data_source.controller;

import com.woody_side.data_source.service.DataSourceService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/resources")
@RequiredArgsConstructor
public class DataSourceController {

    private final DataSourceService dataSourceService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @SneakyThrows
    public ResponseEntity<String> loadCountriesFromFile() {
        return ResponseEntity.ok(dataSourceService.loadCountriesFromFile());
    }
}
