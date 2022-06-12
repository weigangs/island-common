package com.lkyl.island.common.api;

import io.swagger.annotations.ApiModel;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author nicholas
 */
@ApiModel("exampleApi")
@RequestMapping("/example")
public interface ExampleApi {

    /**example for rpc
     *
     * @param body  请求体
     * @return  rpc response entity
     */
    @RequestMapping("/query")
    ResponseEntity<?> query(RequestEntity<?> body);

}
