package ru.geekbrains.services.impl;

import org.springframework.stereotype.Service;
import ru.geekbrains.data.UserData;
import ru.geekbrains.persists.entities.User;
import ru.geekbrains.persists.repositories.RoleRepository;
import ru.geekbrains.persists.repositories.UserRepository;
import ru.geekbrains.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    @Override
    public User createOrUpdate(UserData userData) {
        User user = userData.getId() == null ?
                        new User() :
                        userRepository.getOne(userData.getId());

        user.setName(userData.getName());
        user.setUsername(userData.getUsername());
        user.setPassword(userData.getPassword());
        user.setRoles(userData.getRoles().stream()
                .map(roleRepository::findByName)
                .collect(Collectors.toList()));

        return userRepository.save(user);
    }
}
