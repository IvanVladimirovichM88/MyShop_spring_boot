package ru.geekbrains.services.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.data.UserData;
import ru.geekbrains.persists.entities.Role;
import ru.geekbrains.persists.entities.User;
import ru.geekbrains.persists.repositories.UserRepository;
import ru.geekbrains.services.RoleService;
import ru.geekbrains.services.UserServiceShopUI;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceShopUIImpl implements UserServiceShopUI {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    public UserServiceShopUIImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional <User> user = userRepository.findByUsername(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }

        return new org.springframework.security.core.userdetails.User(
                user.get().getUsername(),
                user.get().getPassword(),
                mapRolesToAuthorities(user.get().getRoles())
        );
    }

    @Override
    public Optional<User> getCurrentUser(Principal principal) {

        if (principal == null){
            return Optional.empty();
        }

        return userRepository.findByUsername(principal.getName());
    }

    @Override
    public Optional<UserData> getCurrentUserData(Principal principal) {

        return this.getCurrentUser(principal).map(UserData::new);

    }

    @Override
    @Transactional
    public User createOrUpdate(UserData userData) {

        if (userData.getId() == null){
            User user = new User();
            user.setName(userData.getName());
            user.setUsername(userData.getUsername());
            user.setPassword(passwordEncoder.encode(userData.getPassword()));

            List<Role> roles = Arrays.asList(roleService.findByName("ROLE_CUSTOMER"));
            user.setRoles(roles);

            return userRepository.save(user);
        }

        User user = userRepository.getOne(userData.getId());

        user.setName(userData.getName());
        user.setUsername(userData.getUsername());
        if( !userData.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(userData.getPassword()));
        }

        return userRepository.save(user);
    }

    @Override
    public User update(UserData updateUser, Principal principal) {

        Optional<User> currentUser = this.getCurrentUser(principal);

        if ( currentUser.isEmpty() ){
            throw new UsernameNotFoundException("No current User");
        }

        boolean isChangInCurrentUser = false;

        if (!updateUser.getName().isBlank() || updateUser.getName().equals(currentUser.get().getName())){
            currentUser.get().setName(updateUser.getName());
            isChangInCurrentUser =  true;
        }

        if (!updateUser.getPassword().isBlank()){
            currentUser.get().setPassword( passwordEncoder.encode(updateUser.getPassword()) );
            isChangInCurrentUser =  true;
        }

        return isChangInCurrentUser? userRepository.save(currentUser.get()) : currentUser.get();
    }

    @Override
    public void authenticateUser(User user) {

        Collection<?extends GrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        authorities),
                null,authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Collection<?extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
