package com.jiaxun.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LicenseCheckInterceptor());
//        registration.addPathPatterns("/login"); //拦截登陆url
        registration.addPathPatterns("/**");  //所有路径都被拦截
        registration.excludePathPatterns(     //添加不拦截路径
//                "/login",     //登录
                "/**/*.html", //html静态资源
                "/**/*.js",   //js静态资源
                "/**/*.css",  //css静态资源
                "/**/*.woff",
                "/**/*.ttf"
        );
    }
}