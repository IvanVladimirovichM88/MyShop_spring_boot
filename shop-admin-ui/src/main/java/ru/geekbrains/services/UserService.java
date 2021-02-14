package ru.geekbrains.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.data.UserData;
import ru.geekbrains.persists.entities.User;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    User getOne(Long id);

    List<User> getAllUsers();

    void remove(Long id);

    void saveOrUpdate(User user);

    Optional<User> getCurrentUser(Principal principal);

    void authenticateUser(User user);

    User createOrUpdate(UserData userData);

}