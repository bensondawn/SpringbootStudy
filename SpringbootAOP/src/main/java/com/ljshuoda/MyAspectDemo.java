package com.ljshuoda;

import com.ljshuoda.Service.MyAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {
        "com.ljshuoda.Controller",
        "com.ljshuoda.Service"
})
public class MyAspectDemo {

//    // 定义切面，在MyAspect类中加注解@Configuration，这个地方就不用注入了。
//    @Bean(name = "myAspect")
//    public MyAspect initMyAspact(){
//        return new MyAspect();
//    }

    public static void main(String[] args){
        SpringApplication.run(MyAspectDemo.class,args);
    }
}
