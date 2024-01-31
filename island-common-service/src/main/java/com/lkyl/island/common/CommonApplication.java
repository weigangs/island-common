package com.lkyl.island.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.lkyl.island.common", "com.lkyl.oceanframework.common"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
        log.info("========>island-common-service started!");
    }
}
