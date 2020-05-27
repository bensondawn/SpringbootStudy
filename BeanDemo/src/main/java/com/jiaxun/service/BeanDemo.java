package com.jiaxun.service;

import com.jiaxun.annotation.ArraySizeNotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.jiaxun.annotation",
        "com.jiaxun.Controller",
        "com.jiaxun.service"
})
public class BeanDemo {

    public static void main(String[] args) {
        SpringApplication.run(BeanDemo.class, args);
    }
}