package com.benson.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.benson.mybatis.controller",
        "com.benson.mybatis.entity",
        "com.benson.mybatis.mapper",
        "com.benson.mybatis.service"
})
@MapperScan("com.benson.mybatis.mapper")
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}