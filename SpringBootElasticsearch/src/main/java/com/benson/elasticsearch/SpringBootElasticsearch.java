package com.benson.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.benson.elasticsearch"
})
public class SpringBootElasticsearch {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootElasticsearch.class, args);
    }
}
