package com.example.lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersController {
    List<UserEntity> userList = new ArrayList<UserEntity>();

    @Autowired
    private UsersService usersService;

    @RequestMapping("/api/users")
    @ResponseBody
    public UsersPage GetUsers(
            @RequestParam(name="page-number", defaultValue = "1") int pageNumber,
            @RequestParam(name="page-size", defaultValue = "20") int pageSize
    ) {
        UsersPage usersPage = new UsersPage();
        usersPage.setPageNumber(pageNumber);
        usersPage.setPageSize(pageSize);
        usersPage.setUsers(userList);

        return usersPage;
    }



}
