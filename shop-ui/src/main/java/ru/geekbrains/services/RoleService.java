package ru.geekbrains.services;


import ru.geekbrains.persists.entities.Role;

public interface RoleService {
    Role findByName(String roleName);

}
