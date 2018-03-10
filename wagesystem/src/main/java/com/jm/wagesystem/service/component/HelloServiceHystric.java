package com.jm.wagesystem.service.component;

import com.jm.wagesystem.service.HelloService;
import org.springframework.stereotype.Component;

/**
 * @Descript: 断路器hystric
 * @Author: liuyingjie
 * @Date: create in 2018/3/10 0010 10:40
 */
@Component
public class HelloServiceHystric implements HelloService {

    /**
     * 当sayHi无法访问的时候返回信息
     * @param name
     * @return
     */
    @Override
    public String sayHi(String name) {

        return "服务暂不可用,"+name;
    }
}
