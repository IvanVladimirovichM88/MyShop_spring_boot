package ru.geekbrains.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.geekbrains.data.UserData;
import ru.geekbrains.persists.entities.User;

import java.security.Principal;
import java.util.Optional;


public interface UserServiceShopUI extends UserDetailsService {

    Optional<User> getCurrentUser(Principal principal);
    Optional <UserData> getCurrentUserData(Principal principal);
    User createOrUpdate(UserData userData);
    User update(UserData userData, Principal principal);
    public void authenticateUser(User user);

}
