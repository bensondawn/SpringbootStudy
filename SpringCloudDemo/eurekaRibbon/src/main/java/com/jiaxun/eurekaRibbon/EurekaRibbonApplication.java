package com.jiaxun.eurekaRibbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix // 启用 hystrix 熔断机制相关配置
@EnableDiscoveryClient // 启用 Eureka 服务发现，该注解不仅仅适用于Eureka，也适用于其它注册中心。
@SpringBootApplication
public class EurekaRibbonApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaRibbonApplication.class, args);
    }
}
