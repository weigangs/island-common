package com.lkyl.island.common.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
