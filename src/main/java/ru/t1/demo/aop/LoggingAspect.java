package ru.t1.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.t1.demo.model.DataSourceErrorLog;
import ru.t1.demo.repository.DataSourceErrorLogRepository;

@Slf4j
@Component
@Aspect
public class LoggingAspect {
    @Autowired
    DataSourceErrorLogRepository dataSourceErrorLogRepository;

    @AfterThrowing(pointcut = "@annotation(ru.t1.demo.aop.annotation.LogDataSourceError)", throwing = "e")
    public void handleException(JoinPoint joinPoint, Exception e) {
        StringBuilder stackTrace = new StringBuilder();
        log.error("AFTER THROWING: " + joinPoint.getSignature().toShortString());
        for (var el : e.getStackTrace()) {
            stackTrace.append(el);
        }
        DataSourceErrorLog dataSourceErrorLog = DataSourceErrorLog.builder()
                .message(e.getMessage())
                .methodSignature(joinPoint.getSignature().toString())
                .stacktrace(stackTrace.toString())
                .build();

        dataSourceErrorLogRepository.save(dataSourceErrorLog);
    }
}
