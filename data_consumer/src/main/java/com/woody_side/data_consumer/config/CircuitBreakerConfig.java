package com.woody_side.data_consumer.config;

import com.woody_side.circuit_breaker.config.CircuitBreakerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CircuitBreakerConfiguration.class)
public class CircuitBreakerConfig {
}
