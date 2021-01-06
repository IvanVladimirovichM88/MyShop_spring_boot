package ru.geekbrains.services;

import org.springframework.stereotype.Service;
import ru.geekbrains.entities.User;
import ru.geekbrains.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOne(Long id){
        return userRepository.getOne(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void remove(Long id){
        userRepository.deleteById(id);
    }

    public void saveOrUpdate(User user){
        userRepository.save(user);
    }


}
