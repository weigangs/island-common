package com.lkyl.island.common.service.controller;

import com.lkyl.oceanframework.log.annotation.LogRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年04月25日 21:27
 */

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping("/check")
    public ResponseEntity<?> health(){
//        if(true)
//            throw new CommonException("CM001");
        return ResponseEntity.ok("health");
    }

    @GetMapping("/test")
    @LogRecord(content = "第一次测试，方法参数为#{#zou}，使用自定义方法【firstTest】解析结果：#{@{firstTest(#zou)}}", bizNo = "001")
    public ResponseEntity<?> testLog(@RequestParam("zou") String zou) {
        return ResponseEntity.ok(zou);
    }

}
