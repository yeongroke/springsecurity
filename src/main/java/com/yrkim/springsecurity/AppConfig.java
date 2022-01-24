package com.yrkim.springsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AppConfig implements CommandLineRunner, HealthIndicator {

    @Override
    public void run(String... args) throws Exception {
        /*
        * TODO 김영록
        *  현재는 바로 실행해야 할 것이 없다..
        * */
    }

    /*@Bean
    @Profile("dev")
    public void init() {

    }*/

    @Override
    public Health health() {
        return Health.up().build();
    }
}
