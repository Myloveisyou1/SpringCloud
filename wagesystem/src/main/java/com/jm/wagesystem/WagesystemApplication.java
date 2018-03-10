package com.jm.wagesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient //表明自己是客户端client
@EnableFeignClients //开启Feign的功能
@SpringBootApplication
public class WagesystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(WagesystemApplication.class, args);
	}
}
