package ru.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persists.entities.Role;
import ru.geekbrains.persists.repositories.RoleRepository;
import ru.geekbrains.services.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllRoles(
            Model model
    ){
        model.addAttribute("roles", roleService.getAllRoles());
        return "roles";
    }

    @GetMapping("/add")
    public String showFormAddRole(
            Model model
    ){
        model.addAttribute("newRole", new Role());
        return "/forms/form_for_role";
    }

    @PostMapping("/add")
    public String addRole(
            @ModelAttribute Role role
    ){
        roleService.saveOrUpdate(role);
        return"redirect:/roles";
    }

    @GetMapping("/delete/{roleId}")
    public String deleteRole(
            @PathVariable Long roleId
    ){
        roleService.remove(roleId);
        return "redirect:/roles";
    }
}
