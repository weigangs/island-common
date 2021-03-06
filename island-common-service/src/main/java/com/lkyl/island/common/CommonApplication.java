package com.lkyl.island.common;

import com.lkyl.oceanframework.log.annotation.EnableLogRecord;
import com.lkyl.oceanframework.mybatis.annotation.EnableOceanMybatis;
import com.lkyl.oceanframework.security.annotation.OceanOauth2Server;
import com.lkyl.oceanframework.web.annotation.EnableOceanWeb;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@OceanOauth2Server
@EnableOceanMybatis
@EnableOceanWeb
@SpringBootApplication
@EnableLogRecord(tenantId = "dev")
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
        log.info("========>island-common-service started!");
    }
}
