package com.egobuy.egobuysearchservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yadonghe
 * @date 2020-03-07 15:55
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
