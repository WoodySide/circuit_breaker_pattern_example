package com.woody_side.data_consumer.aspect.exception_handling;

import com.woody_side.data_consumer.exception.DataSourceRespondDelayException;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    @SneakyThrows
    public boolean hasError(ClientHttpResponse httpResponse) {
        return httpResponse.getStatusCode().is5xxServerError();
    }

    @Override
    @SneakyThrows
    public void handleError(ClientHttpResponse httpResponse) {
        if (httpResponse.getStatusCode().is5xxServerError()) {
            throw new DataSourceRespondDelayException();
        }
    }
}
