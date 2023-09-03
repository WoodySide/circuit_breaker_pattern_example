package com.woody_side.data_source.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j(topic = "Controller layer aspect")
@Component
@Aspect
public class LogControllerAspect {

    @Pointcut(value = "within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller(){}

    @Before(value = "controller()")
    public void beforeController(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.info("=====>  {} received HTTP request: [{} -> {}]",
                joinPoint.getSignature().getDeclaringTypeName(),
                request.getMethod(),
                request.getRequestURI());
        log.info("=====>   Attributes, processed by controller:  {}", Arrays.toString(joinPoint.getArgs()));
    }

    @Around(value = "controller()")
    public Object timer(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch clock = new StopWatch(joinPoint.toString());
        try {
            clock.start(joinPoint.toShortString());
            return joinPoint.proceed();
        } finally {
            clock.stop();
            log.info(clock.prettyPrint());
        }
    }

    @AfterReturning(
            pointcut = "controller()",
            returning = "result"
    )
    public void afterController(JoinPoint joinPoint, Object result) {
        log.info("=====> {} returned {} ",
                joinPoint.getSignature().getDeclaringTypeName(),
                result);
    }
}
