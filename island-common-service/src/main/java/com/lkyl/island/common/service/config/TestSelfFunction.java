package com.lkyl.island.common.service.config;

import com.lkyl.oceanframework.log.function.IParseFunction;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年07月10日 16:31
 */
@Component
public class TestSelfFunction implements IParseFunction {
    @Override
    public String functionName() {
        return "firstTest";
    }

    @Override
    public String apply(String s) {
        return s.toUpperCase();
    }
}
