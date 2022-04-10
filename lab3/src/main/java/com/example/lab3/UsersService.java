package com.example.lab3;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PreDestroy;
import java.util.Collection;

@Service
@Scope()
public class UsersService {

    public UsersPage getUsers(int pageNumber,int pageSize)
    {
        return null;
    }
}
