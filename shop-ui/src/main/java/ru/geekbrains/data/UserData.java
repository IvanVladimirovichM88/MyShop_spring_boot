package ru.geekbrains.data;

import ru.geekbrains.persists.entities.Role;
import ru.geekbrains.persists.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserData {
    private Long id;
    private String name;
    private String username;
    private String password;

    public UserData(){}

    public  UserData(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.password = user.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
