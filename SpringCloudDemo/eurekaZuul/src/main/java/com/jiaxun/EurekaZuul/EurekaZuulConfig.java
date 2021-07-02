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

    @Bean
    public RoutingFilter routingFilter(){return new RoutingFilter();}

    @Bean
    public PostFilter postFilter(){return new PostFilter();}

    @Bean
    public ErrorFilter errorFilter(){return new ErrorFilter();}
}
