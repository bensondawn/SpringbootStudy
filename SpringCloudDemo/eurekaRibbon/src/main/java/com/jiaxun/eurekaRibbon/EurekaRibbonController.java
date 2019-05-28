package com.jiaxun.eurekaRibbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaRibbonController {

    @Autowired
    private EurekaRibbonService eurekaRibbonService;

    @RequestMapping("/ribbonInfo")
    public String ribbonInfo() {
        String message = eurekaRibbonService.getInfo();
        return "获取的信息:" + message;
    }
}
