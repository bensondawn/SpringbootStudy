package com.jiaxun.EurekaZuul;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

    /**
     * PRE：这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
     * ROUTING：这种过滤器将请求路由到微服务。这种过滤器用于构建发送给微服务的请求，并使用Apache HttpClient或Netfifilx Ribbon请求微服务。
     * POST：这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
     * ERROR：在其他阶段发生错误时执行该过滤器。
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE; // 在请求被路由之前调用
    }

    @Override
    public int filterOrder() {
        return 1;//FilterConstants.PRE_DECORATION_FILTER_ORDER; // filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true; // 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss SSS");
        LOGGER.info("******PostFilter*****" + sdf.format(d) + "*******PostFilter*******");
        return null;
    }
}