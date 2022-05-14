package com.example.lab3;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {
    List<UserEntity> userList = new ArrayList<UserEntity>();

    @Autowired
    private UsersService usersService;

    @RequestMapping("/api/users")
    public ResponseEntity<?> GetUsers(
            @RequestParam(name="page-number", defaultValue = "1") int pageNumber,
            @RequestParam(name="page-size", defaultValue = "20") int pageSize
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.usersService.getUsers(pageNumber, pageSize));
    }

    @RequestMapping(
            value = "/api/user/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> CreateUser(
            @RequestParam(name="page-number", defaultValue = "1") int pageNumber,
            @RequestParam(name="page-size", defaultValue = "20") int pageSize
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.usersService.getUsers(pageNumber, pageSize));
    }

    @RequestMapping(
            value = "api/users/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity getUser(@PathVariable Integer id) throws JsonProcessingException {
        return usersService.getUser(id);
    }

    @RequestMapping(
            value = "api/users/{id}/update",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) throws JsonProcessingException {
        try {
            return usersService.updateUser(id, user);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\":\"true\"}");
        }
    }

    @RequestMapping(
            value = "api/users/{id}/remove",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity deleteUser(@PathVariable Integer id)
    {
        try {
            usersService.deleteUsers(id);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"true\"}");
        }

        return null;
    }

}
