package com.lkyl.island.common.service.controller;

import com.lkyl.island.common.ps.service.UserService;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.CommonResult;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/login/{username}")
    public ResponseEntity<?> login(@PathVariable("username") String username){
        log.info("login starting ...");
        if(StringUtils.isEmpty(username)){
            throw  new CommonException(CommonCode.EXCEPTION, "用户名为空!");
        }

        return ResponseEntity.ok(new CommonResult().
                setCode(CommonCode.SUCCESS).
                setMsg("获取成功").
                setData(userService.login(username)));
    }
}
