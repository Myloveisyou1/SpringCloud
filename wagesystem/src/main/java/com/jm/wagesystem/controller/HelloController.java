package com.jm.wagesystem.controller;

import com.jm.wagesystem.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Descript:
 * @Author: liuyingjie
 * @Date: create in 2018/3/10 0010 10:30
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService service;

    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {

        return service.sayHi(name);
    }
}
