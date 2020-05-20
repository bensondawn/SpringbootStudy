package com.jiaxun.eurekaFeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaFeignController {

    @Autowired
    private EurekaFeignService eurekaFeignService;

    @Autowired
    private ConfigClientFeignService configClientFeignService;

    @RequestMapping("/feign-client-info")
    public String feignClientInfo() {
        String message = eurekaFeignService.getClientInfo();
        return "获取到的信息:" + message;
    }

    @RequestMapping("/feign-config-info")
    public String feignConfigInfo() {
        String message = configClientFeignService.getConfigInfo();
        return "获取到的信息:" + message;
    }
}
