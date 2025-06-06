package ru.t1.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic createT1DemoMetrics() {
        return TopicBuilder.name("t1_demo_metrics")
                .build();
    }
}
