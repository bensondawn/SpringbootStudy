package com.jiaxun.eurekaFeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaFeignController {

    @Autowired
    private EurekaFeignService eurekaFeignService;

    @RequestMapping("/feignInfo")
    public String feignInfo() {
        String message = eurekaFeignService.getInfo();
        return "获取到的信息:" + message;
    }
}
