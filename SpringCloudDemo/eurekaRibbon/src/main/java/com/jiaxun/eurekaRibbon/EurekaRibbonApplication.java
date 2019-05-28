package com.jiaxun.eurekaRibbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix // 启用 hystrix 熔断机制相关配置
@EnableEurekaClient // 启用 Eureka 服务发现 相关配置
@SpringBootApplication
public class EurekaRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaRibbonApplication.class, args);
    }
}
