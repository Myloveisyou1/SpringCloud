package com.testwages;

import com.utils.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestWagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestWagesApplication.class, args);

		Test test = new Test();
		System.out.println(test.getName());
	}
}
