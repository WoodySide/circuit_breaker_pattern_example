package com.woody_side.data_consumer.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j(topic = "Service layer aspect")
@Component
@Aspect
public class LogServiceAspect {

    @Pointcut(value = "within(@org.springframework.stereotype.Service *)")
    public void service(){}

    @Before(value = "service()")
    public void beforeService(JoinPoint joinPoint) {
        log.info("=====>  {} starts executing method: [{}] with attributes {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(
            pointcut = "service()",
            returning = "result"
    )
    public void afterService(JoinPoint joinPoint, Object result) {
        log.info("=====>   Method: [{}] of the service {} returned {}",
                joinPoint.getSignature().getName(),
                joinPoint.getSignature().getDeclaringTypeName(),
                result);
    }
}
