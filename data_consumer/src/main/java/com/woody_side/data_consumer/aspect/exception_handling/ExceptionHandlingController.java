package com.woody_side.data_consumer.aspect.exception_handling;

import com.woody_side.data_consumer.dto.Error;
import com.woody_side.data_consumer.exception.DataSourceRespondDelayException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlingController {

    @ExceptionHandler(value = CallNotPermittedException.class)
    @ResponseStatus(value = SERVICE_UNAVAILABLE)
    public ResponseEntity<Error> handleCallNotPermittedException(DataSourceRespondDelayException exception, WebRequest webRequest) {
        var error = Error.builder()
                .message(exception.getMessage())
                .path(webRequest.getContextPath())
                .build();

        return new ResponseEntity<>(error, SERVICE_UNAVAILABLE);
    }
}
