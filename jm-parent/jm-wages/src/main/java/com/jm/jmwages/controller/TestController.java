package com.jm.jmwages.controller;

import com.jm.jmapi.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Descript:
 * @Author: liuyingjie
 * @Date: create in 2018/3/8 0008 17:29
 */
@RestController
public class TestController {

    @RequestMapping(value = "/test")
    public String test() {
        User user = new User();
        return user.getName();
    }
}
