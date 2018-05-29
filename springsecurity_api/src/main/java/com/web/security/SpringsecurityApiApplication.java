package com.web.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;

@SpringBootApplication
@MapperScan("com.web.security.dao")//将项目中对应的mapper类的路径加进来就可以了
public class SpringsecurityApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApiApplication.class, args);
	}


}
