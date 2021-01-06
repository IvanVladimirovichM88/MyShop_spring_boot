package ru.geekbrains.services;

import ru.geekbrains.persists.entities.User;

import java.util.List;

public interface UserService {

    User getOne(Long id);

    List<User> getAllUsers();

    void remove(Long id);

    void saveOrUpdate(User user);
}