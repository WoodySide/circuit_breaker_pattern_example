package com.woody_side.data_consumer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class DataSourceRespondDelayException extends RuntimeException {

    private static final String MSG = "No respond from the service!";

    public DataSourceRespondDelayException() {
        super(MSG);
    }
}
