package com.ljshuoda.Controller;

import com.ljshuoda.Service.User;
import com.ljshuoda.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService = null;

    // 定义请求
    @RequestMapping("/print")
    @ResponseBody
    public User printUser(){
        User user = new User();
        user.setId(1L);
        user.setUsername("zhang");
        user.setNote("fasfsad");
        userService.printUser(user);
        return user;
    }
}
