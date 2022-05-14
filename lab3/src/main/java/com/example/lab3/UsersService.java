package com.example.lab3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.io.*;

@Service
@Scope()
public class UsersService {

    HashMap<Long, UserEntity> userList;

    @PostConstruct
    private void onCreate() throws IOException {
        // wczytywanie uzytkowników
        try (FileInputStream inputStream = new FileInputStream("users.json")) {
            File file = new File("users.json");
            InputStreamReader reader = new InputStreamReader(inputStream);
            ObjectMapper objectMapper = new ObjectMapper();
            userList = objectMapper.readValue(reader, HashMap.class);
        }
    }

    @PreDestroy
    private void onDestroy() throws IOException {
        // zapisywanie uzytkowników
        FileOutputStream file = new FileOutputStream("users.json");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(file, StandardCharsets.UTF_8);
        BufferedWriter writer = new BufferedWriter(outputStreamWriter);
        ObjectMapper objectMapper = new ObjectMapper();
        writer.write(objectMapper.writeValueAsString(userList));
        writer.flush();
    }

    public UsersPage getUsers(int pageNumber,int pageSize)
    {
        UsersPage usersPage = new UsersPage();
        usersPage.setPageNumber(pageNumber);
        usersPage.setPageSize(pageSize);
        usersPage.setTotalCount(userList.size());
        usersPage.setUsers(this.userList.values());

        return usersPage;
    }

    public ResponseEntity getUser(Integer id) throws JsonProcessingException {
        if (userList.get(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(userList.get(id));
        }
    }

    public UserEntity addUser(String name, int age)
    {
        long newID = (long)(userList.size() + 1);
        UserEntity newUser = new UserEntity(newID, name, age);
        userList.put(newID, newUser);

        return newUser;
    }

    public ResponseEntity updateUser(Long id, UserEntity user) throws JsonProcessingException {
        user.setId(id);

        if (userList.get(id) != null) {
            userList.put(id, user);

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(user);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity deleteUsers(Integer id) {
        if (userList.get(id) != null) {
            userList.remove(id);

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"result\":\"true\"}");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
