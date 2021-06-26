package com.dant.mqtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class MqtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqtestApplication.class, args);
	}

}
