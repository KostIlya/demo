package ru.t1.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.t1.demo.config.MetricConfig;
import ru.t1.demo.model.TimeLimitExceedLog;
import ru.t1.demo.repository.TimeLimitExceedLogRepository;

import java.time.LocalDateTime;

@Slf4j
@Component
@Aspect
public class MetricAspect {
    @Autowired
    private TimeLimitExceedLogRepository timeLimitExceedLogRepository;
    @Autowired
    private MetricConfig metricConfig;
    @Around("@annotation(ru.t1.demo.aop.annotation.Metric)")
    public Object timeRunningMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("MetricAspect.timeRunningMethod: запущен");
        long start = System.currentTimeMillis();

        Object proceed = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();

        long executionTime = end - start;

        if (executionTime > metricConfig.getTimeLimit()) {
            TimeLimitExceedLog timeLimitExceedLog = TimeLimitExceedLog.builder()
                    .executionTime(executionTime)
                    .limitTime(metricConfig.getTimeLimit())
                    .dateTime(LocalDateTime.now())
                    .methodSignature(proceedingJoinPoint.getSignature().toString())
                    .build();
            timeLimitExceedLogRepository.save(timeLimitExceedLog);
        }
        log.info("MetricAspect.timeRunningMethod: завершен. Время выполнения метода {} мс", executionTime);
        return proceed;
    }
}
