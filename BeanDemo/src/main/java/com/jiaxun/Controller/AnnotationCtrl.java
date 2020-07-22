package com.jiaxun.Controller;

import com.jiaxun.annotation.MyFirstAnnotation;
import com.jiaxun.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AnnotationCtrl {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @MyFirstAnnotation("吃饭")
    @RequestMapping(value = "/say")
    public String requestPermissions() {
        System.out.println("吃饭");
        return "hello spring boot";
    }

    @PostMapping("/alarm")
    public Map<String, Object> createOrders(@RequestBody @Valid UserModel userModel, BindingResult results) {
        Map<String, Object> errMap = new HashMap<>();
        List<ObjectError> oes = results.getAllErrors();
        for (ObjectError oe : oes) {
            String key = null;
            String msg = null;
            // 字段错误
            if (oe instanceof FieldError) {
                FieldError fe = (FieldError) oe;
                key = fe.getField();// 获取错误验证字段名
            } else {
                // 非字段错误
                key = oe.getObjectName();// 获取验证对象名称
            }
            // 错误信息
            msg = oe.getDefaultMessage();
            errMap.put(key, msg);
        }
        return errMap;
    }
}
