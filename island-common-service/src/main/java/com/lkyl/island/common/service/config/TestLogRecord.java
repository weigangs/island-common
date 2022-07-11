package com.lkyl.island.common.service.config;

import com.lkyl.oceanframework.log.annotation.LogRecord;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年07月11日 23:54
 */
@Component
public class TestLogRecord {

    @LogRecord(content = "开始访问系统，IP为【#{@{ipFunction(#request)}}】，访问路径为【#{#request.getRequestURI()}】", bizNo = "0")
    public void doIpLog(HttpServletRequest request) {
        System.out.println("log");
    }
}
