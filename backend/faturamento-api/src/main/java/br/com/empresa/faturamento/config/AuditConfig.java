package br.com.empresa.faturamento.config;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditConfig {

    @Bean
    Clock systemClock() {
        return Clock.systemDefaultZone();
    }
}
