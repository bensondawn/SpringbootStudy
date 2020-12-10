package com.jiaxun.configClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RefreshScope
@RestController
public class ConfigClientController {

    @Value("${spring.datasource.druid.password}")
    private String info;

    /**
     * 提供的一个restful服务
     *
     * @return 返回  配置中的info信息
     */
    @RequestMapping("/info")
    public String info(HttpServletRequest request) {
        String message = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getServletPath() + ";info:" + info;
        System.out.println("***********" + message + "***********");
        return message;
    }
}
