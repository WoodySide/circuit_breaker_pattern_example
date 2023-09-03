package com.woody_side.circuit_breaker.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfiguration {

    @Bean
    public CircuitBreaker timeBasedCircuitBreaker() {
        var circuitBreakerConfig = CircuitBreakerConfig.custom()
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
                .minimumNumberOfCalls(1) // after 1 successful one, next will change the state to OPEN
                .slidingWindowSize(10)
                .waitDurationInOpenState(Duration.ofSeconds(5))
                .failureRateThreshold(65.0f)
                .slowCallDurationThreshold(Duration.ofSeconds(3))
                .build();

        var circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);

        return circuitBreakerRegistry.circuitBreaker("Countries data consumer circuitBreaker");
    }
}
