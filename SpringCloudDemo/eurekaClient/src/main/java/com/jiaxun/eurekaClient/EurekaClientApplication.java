package com.jiaxun.eurekaClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 工程名称不要起EurekaClient，否则编译不通过，跟EurekaClient的依赖起冲突了
 * 早先EurekaClientApplication名称起的EurekaClient，编译不通过，后来改为EurekaClientApplication
 * 编译通过。
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class,args);
    }
}
