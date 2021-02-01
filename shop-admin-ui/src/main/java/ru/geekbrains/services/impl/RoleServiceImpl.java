package ru.geekbrains.services.impl;

import org.springframework.stereotype.Service;
import ru.geekbrains.persists.entities.Role;
import ru.geekbrains.persists.repositories.RoleRepository;
import ru.geekbrains.services.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.getOne(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void remove(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

}
