package com.jiaxun.webfilter;

import com.jiaxun.EurekaZuul.AuthFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 此处是为了验证webfilter、zuulfilter、interceptor的执行顺序；webfilter早于zuulfilter执行。
 * 由于interceptor是在controller前后执行，所以该包下如果没有controller，拦截器不执行。
 * 如果在该包下增加controller，则gateway失去了转发功能，zuulfilter就不执行了。
 * 所以controller需要放在其它有具体controller业务的微服务中，或者放到feign中。
 */
@Configuration
@WebFilter(filterName = "myWebFilter",urlPatterns = "/*")
@Order(100)
public class myWebFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss SSS");
        LOGGER.info("******myWebFilter*****" + sdf.format(d) + "*******myWebFilter*******");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
