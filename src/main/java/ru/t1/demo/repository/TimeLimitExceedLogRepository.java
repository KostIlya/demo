package ru.t1.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.demo.model.TimeLimitExceedLog;

public interface TimeLimitExceedLogRepository extends JpaRepository<TimeLimitExceedLog, Long> {
}
