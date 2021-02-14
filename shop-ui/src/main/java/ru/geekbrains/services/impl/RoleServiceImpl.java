package ru.geekbrains.services.impl;

import org.springframework.stereotype.Service;
import ru.geekbrains.persists.entities.Role;
import ru.geekbrains.persists.repositories.RoleRepository;
import ru.geekbrains.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
