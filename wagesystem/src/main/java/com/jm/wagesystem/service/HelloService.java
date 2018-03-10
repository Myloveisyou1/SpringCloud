package com.jm.wagesystem.service;

import com.jm.wagesystem.service.component.HelloServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Descript:
 * @Author: liuyingjie
 * @Date: create in 2018/3/10 0010 10:33
 */
@FeignClient(value = "service-hi",fallback = HelloServiceHystric.class)
public interface HelloService {

    @GetMapping(value = "/hi")
    String sayHi(@RequestParam(value = "name") String name);
}
