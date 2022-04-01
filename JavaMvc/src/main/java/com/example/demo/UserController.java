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

    private HashMap<Integer, UserEntity> userList = new HashMap<Integer, UserEntity>(){{
            put(1, new UserEntity(1L, "Artur", 22));
    }};

    @RequestMapping("/users")
    @ResponseBody
    public String users() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            String usersJson = mapper.writeValueAsString(userList);
            return usersJson;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Not found";
        }
    }

    @RequestMapping("/user/{id}/get")
    @ResponseBody
    public String GetUser(
            @PathVariable Long id
    ) {

        UserEntity userEntity = userList.get(id);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String userJson = mapper.writeValueAsString(userEntity);
            return userJson;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Not found";
        }
    }

    @RequestMapping("/user/{id}/add")
    @ResponseBody
    public String RemoveUser(
            @RequestParam String name,
            @RequestParam Integer age
    ) {

        int userLength = userList.size() + 1;
        UserEntity newUser = new UserEntity((long)userLength, name, age);
        userList.put(userLength, newUser);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String userJson = mapper.writeValueAsString(newUser);
            return userJson;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Not found";
        }
    }

    @RequestMapping("/user/{id}/remove")
    @ResponseBody
    public String RemoveUser(
            @PathVariable Long id
    ) {

        userList.remove(id);

        return "User has been deleted";
    }
}
