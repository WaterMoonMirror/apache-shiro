package com.example.apacheshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.Mapping;

@SpringBootApplication
@ComponentScan
@MapperScan(basePackages = {"com.example.apacheshiro.mapper"})
public class ApacheShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApacheShiroApplication.class, args);
    }
}
