package ru.t1.demo.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
public class MetricAspect {
    @Autowired
    private final TimeLimitExceedLogRepository timeLimitExceedLogRepository;
    @Autowired
    private final MetricConfig metricConfig;
    @Around("@annotation(ru.t1.demo.aop.annotation.Metric)")
    public Object timeRunningMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("timeRunningMethod(): running");
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
        log.info("timeRunningMethod(): completed. Method execution time {} ms", executionTime);
        return proceed;
    }
}
