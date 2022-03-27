package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

public class UserController {

//    private Map<Integer, UserEntity> = new HashMap({});

    @RequestMapping("/users")
    @ResponseBody
    public String users() {
        return "ss";
    }

    @RequestMapping("/user/{id}/get")
    @ResponseBody
    public UserEntity user(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam(defaultValue = "5") Integer age
    ) {

        UserEntity userEntity = new UserEntity(id, name, age);

        return userEntity;
    }
}
