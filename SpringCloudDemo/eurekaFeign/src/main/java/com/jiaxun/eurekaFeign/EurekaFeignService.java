package com.jiaxun.eurekaFeign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "eureka-client", fallback = EurekaFeignServiceFailure.class) // 调用的服务的名称
public interface EurekaFeignService {

    @RequestMapping("/demo/v1/client/info")
    String getInfo();
}
