package com.xiaoxin.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.xiaoxin")
@MapperScan("com.xiaoxin.entity.mapper")
@EnableScheduling
public class WxPushApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxPushApiApplication.class, args);
	}

}
