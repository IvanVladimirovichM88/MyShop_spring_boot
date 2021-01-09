package ru.geekbrains.utils;

import org.springframework.stereotype.Component;
import ru.geekbrains.persists.entities.Role;
import ru.geekbrains.persists.entities.User;
import ru.geekbrains.persists.repositories.RoleRepository;
import ru.geekbrains.persists.repositories.UserRepository;

import javax.annotation.PostConstruct;

//@Component
public class SampleData {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public SampleData(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init(){
        Role role1 = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");
        Role role3 = new Role("ROLE_SUPER_ADMIN");
        Role role4 = new Role("ROLE_MANAGER");

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
        roleRepository.save(role4);

        User user1 = new User("Alex", "alex", "1");
        user1.getRoles().add(role1);
        user1.getRoles().add(role2);
        user1.getRoles().add(role3);

        User user2 = new User("Alena", "alena", "1");
        user2.getRoles().add(role1);
        user2.getRoles().add(role4);

        userRepository.save(user1);
        userRepository.save(user2);

    }
}
