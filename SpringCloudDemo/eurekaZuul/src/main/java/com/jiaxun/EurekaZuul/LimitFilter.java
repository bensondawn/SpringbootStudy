package com.jiaxun.EurekaZuul;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class LimitFilter extends ZuulFilter {

    // 创建限制每秒最高的请求数。此处是并发编程，各个线程需共享变量，所以定义为static volatile
    private static volatile RateLimiter rateLimiter = RateLimiter.create(10.0);

    public LimitFilter(){
        super();
    }

    @Override
    public boolean shouldFilter(){
        return true;
    }

    @Override
    public String filterType(){
        return "pre";
    }

    @Override
    public int filterOrder(){
        return 0;
    }

    @Override
    public Object run(){
        // 总体限流。
        // 从RateLimite获取一个令牌，该方法会被阻塞直到获取令牌。
        // 返回等待的时间。
        return rateLimiter.acquire();
    }
}
