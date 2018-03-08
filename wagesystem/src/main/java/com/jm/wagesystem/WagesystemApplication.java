package com.jm.wagesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient //表明自己是客户端client
@SpringBootApplication
public class WagesystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(WagesystemApplication.class, args);
	}
}
