package ru.geekbrains.services;

import ru.geekbrains.persists.entities.Role;

import java.util.List;

public interface RoleService {

    Role getById(Long id);

    List<Role> getAllRoles();

    void remove(Long id);

    void saveOrUpdate(Role role);

    Role findByName(String name);
}
