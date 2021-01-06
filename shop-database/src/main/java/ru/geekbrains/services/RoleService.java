package ru.geekbrains.services;



import org.springframework.stereotype.Service;
import ru.geekbrains.entities.Role;
import ru.geekbrains.repositories.RoleRepository;


import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;

/////////////////////////////////////////////////////

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getById(Long id){
        return roleRepository.getOne(id);
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public void remove(Long id){
        roleRepository.deleteById(id);
    }

    public void saveOrUpdate(Role role){
        roleRepository.save(role);
    }

    public Role findByName(String name){
        return roleRepository.findByName(name);
    }
}
