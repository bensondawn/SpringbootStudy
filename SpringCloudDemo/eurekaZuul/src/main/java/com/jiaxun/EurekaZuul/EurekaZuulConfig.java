package com.jiaxun.EurekaZuul;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EurekaZuulConfig {

    @Bean
    public LimitFilter limitFilter(){
        return new LimitFilter();
    }

    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }
}
