package com.ljshuoda;

import com.ljshuoda.service.HelloService;
import com.ljshuoda.service.HelloServiceImpl;
import com.ljshuoda.service.MyInterceptor;
import com.ljshuoda.service.ProxyBean;

public class ProxyDemo {

    public static void main(String[] args){

        HelloService helloService = new HelloServiceImpl();
        // 按照约定获取proxy
        HelloService proxy = (HelloService) ProxyBean.getProxyBean(helloService,new MyInterceptor());
        proxy.sayHello("zhangsan");
    }

}
