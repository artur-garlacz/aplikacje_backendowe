package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    private HashMap<Long, UserEntity> userList = new HashMap<Long, UserEntity>(){{
        put(1L, new UserEntity(1L, "Artur", 22));
        put(2L, new UserEntity(2L, "Natalia", 23));
        put(3L, new UserEntity(3L, "Julia", 17));
    }};

    @RequestMapping("/users")
    @ResponseBody
    public HashMap<Long, UserEntity> users() {
        return userList;
    }

    @RequestMapping("/user/{id}/get")
    @ResponseBody
    public UserEntity user(
            @PathVariable Long id
    ) {

        return userList.get(id);
    }

    @RequestMapping("/users/add")
    @ResponseBody
    public UserEntity RemoveUser(
            @RequestParam String name,
            @RequestParam Integer age
    ) {

        long userLength = userList.size() + 1;
        UserEntity newUser = new UserEntity(userLength, name, age);
        userList.put(userLength, newUser);

        return newUser;
    }

    @RequestMapping("/user/{id}/remove")
    @ResponseBody
    public String RemoveUser(
            @PathVariable Long id
    ) {
        try {
            userList.remove(id);
        } catch (Exception e) {
            return "Error while deleting users: " + e.getMessage();
        }

        return "User with id: " + id + "has been deleted!";
    }
}
