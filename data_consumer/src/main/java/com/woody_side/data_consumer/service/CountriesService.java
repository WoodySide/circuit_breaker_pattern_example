package com.woody_side.data_consumer.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.woody_side.data_consumer.dto.CountriesDto;
import com.woody_side.data_consumer.mapper.CountriesDtoMapper;
import com.woody_side.data_consumer.model.Countries;
import com.woody_side.data_consumer.proxy.DataSourceProxy;
import com.woody_side.data_consumer.repository.CountriesRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.fallback.FallbackDecorator;
import io.github.resilience4j.fallback.FallbackDecorators;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountriesService {

    private final CountriesRepository countriesRepository;
    private final DataSourceProxy dataSourceProxy;
    private final CircuitBreaker circuitBreaker;

    public List<CountriesDto> saveCountriesData() {

        var countriesSupplier = circuitBreaker.decorateSupplier(
                () -> getResponseContent(dataSourceProxy.getResource()));

        log.info("Circuit breaker state is in process");

        var jsonObject = countriesSupplier.get();

        if(jsonObject.isJsonNull()) {
            throw new IllegalArgumentException("Can't fetch data from data source!");
        }

        var strings = jsonObject.keySet();

        Map<String,String> map = new HashMap<>();

        for(String shorties: strings) {
            JsonPrimitive value = jsonObject.getAsJsonPrimitive(shorties);
            map.put(shorties, value.getAsString());
        }

        List<Countries> countries = new ArrayList<>();

        for(Map.Entry<String, String> entry: map.entrySet()) {
            countries.add(Countries.builder()
                    .shortName(entry.getKey())
                    .fullName(entry.getValue())
                    .build());
        }

        countriesRepository.saveAll(countries);

        return countries.stream()
                .map(CountriesDtoMapper::toCountriesDto)
                .toList();
    }

    private JsonObject getResponseContent(String response) {
        var element = new JsonParser().parse(response);
        return element.getAsJsonObject();
    }
}
