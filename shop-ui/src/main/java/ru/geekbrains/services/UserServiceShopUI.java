package ru.geekbrains.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.geekbrains.data.UserData;
import ru.geekbrains.persists.entities.User;

import java.security.Principal;


public interface UserServiceShopUI extends UserDetailsService {

    UserData getCurrentUser(Principal principal);
    User createOrUpdate(UserData userData);
    public void authenticateUser(User user);

}
