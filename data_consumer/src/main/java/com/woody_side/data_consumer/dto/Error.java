package com.woody_side.data_consumer.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Value
public class Error {
    String message;
    String path;
}
