package ru.t1.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class MetricAspect {
    @Around("@annotation(ru.t1.demo.aop.annotation.Metric)")
    public void timeRunningMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();


    }
}
