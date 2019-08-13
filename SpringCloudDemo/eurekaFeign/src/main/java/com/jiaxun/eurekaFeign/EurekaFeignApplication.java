package com.jiaxun.eurekaFeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 服务消费者
 * 创建服务消费者根据使用 API 的不同，大致分为三种方式。虽然大家在实际使用中用的应该都是 Feign，但是这里还是把这三种都介绍一下。
 * 1、LoadBalancerClient 2、Spring Cloud Ribbon 3、Spring Cloud Feign
 * feign 是对Ribbon的进一步的封装，省略了RestTemplate的封装，简化了使用Ribbon时自行封装服务调用客户端的开发量。
 */
@EnableHystrix // Feign默认是开启，这个注解可以不加的
@EnableDiscoveryClient // 启用 Eureka 服务发现
@EnableFeignClients // 启用 Feign
@SpringBootApplication
public class EurekaFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaFeignApplication.class, args);
    }
}
