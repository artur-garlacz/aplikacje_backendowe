package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

//    @RequestMapping("/user/{id}")
//    @ResponseBody
//    public UserEntity user(
//            @PathVariable Long id,
//            @RequestParam String name,
//            @RequestParam(defaultValue = "5") Integer age
//    ) {
//
//        UserEntity userEntity = new UserEntity(id, name, age);
//
//        return userEntity;
//    }

    @RequestMapping("/example")
    public String example(
            @RequestParam Integer wariant
    ) {
        if(wariant == 2){
            return "test";
        }
        return "example";
    }
}