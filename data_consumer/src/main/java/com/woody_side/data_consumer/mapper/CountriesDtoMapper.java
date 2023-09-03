package com.woody_side.data_consumer.mapper;

import com.woody_side.data_consumer.dto.CountriesDto;
import com.woody_side.data_consumer.model.Countries;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CountriesDtoMapper {

    public CountriesDto toCountriesDto(Countries countries) {
        return CountriesDto.builder()
                .id(countries.getId())
                .shortName(countries.getShortName())
                .fullName(countries.getFullName())
                .build();
    }
}
