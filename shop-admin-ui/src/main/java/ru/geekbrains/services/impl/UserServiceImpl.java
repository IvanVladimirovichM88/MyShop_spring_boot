package ru.geekbrains.services.impl;

import org.springframework.stereotype.Service;
import ru.geekbrains.persists.entities.User;
import ru.geekbrains.persists.repositories.UserRepository;
import ru.geekbrains.services.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }
}
