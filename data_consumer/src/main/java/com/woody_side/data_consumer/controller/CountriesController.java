package com.woody_side.data_consumer.controller;

import com.woody_side.data_consumer.dto.CountriesDto;
import com.woody_side.data_consumer.service.CountriesService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/countries")
@RequiredArgsConstructor
public class CountriesController {

    private final CountriesService countriesService;

    @GetMapping(path = "/")
    public ResponseEntity<List<CountriesDto>> saveCountriesData() {
       return ResponseEntity.ok(countriesService.saveCountriesData());
    }
}