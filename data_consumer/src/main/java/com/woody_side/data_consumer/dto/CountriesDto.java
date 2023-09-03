package com.woody_side.data_consumer.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CountriesDto {
    String id;
    @JsonProperty(value = "short_name")
    String shortName;
    @JsonProperty(value = "full_name")
    String fullName;
}
