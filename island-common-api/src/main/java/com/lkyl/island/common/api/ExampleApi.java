package com.lkyl.island.common.api;

import io.swagger.annotations.ApiModel;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@ApiModel("exampleApi")
@RequestMapping("/exmaple")
public interface ExampleApi {

    @RequestMapping("/query")
    ResponseEntity<?> query(RequestEntity<?> body);

}
