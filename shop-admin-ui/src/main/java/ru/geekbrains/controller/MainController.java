package ru.geekbrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.geekbrains.data.UserData;
import ru.geekbrains.entities.Role;
import ru.geekbrains.entities.User;
import ru.geekbrains.services.RoleService;
import ru.geekbrains.services.UserService;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class MainController {

    private UserService userService;
    private RoleService roleService;

    public MainController(UserService userService,RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String indexPage(
            Model model
    ){
        model.addAttribute("activePage", "None");
        return "index";
    }

    @GetMapping("/users")
    public String showAllUsers(
            Model model
    ){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/user/add")
    public String showFormAddUser(
            Model model
    ){
        model.addAttribute("userData", new UserData());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "form_add_user";
    }

    @PostMapping("/user/add")
    public String addUser(
            @ModelAttribute UserData userData
    ){
        User user = new User();
        user.init(userData);
        user.getRoles().addAll( userData.getRoles().stream().map(roleService::findByName).collect(Collectors.toList()));
        userService.saveOrUpdate(user);
        return "redirect:/users";
    }

    @GetMapping("/user/edit/{idUser}")
    public String showFormEditUser(
            @PathVariable Long idUser,
            Model model
    ){
        User user = userService.getOne(idUser);
        model.addAttribute("userData", new UserData(user));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "form_edit_user";
    }

    @PostMapping("/user/edit")
    public String editUser(
            @ModelAttribute UserData userData
    ){
        User user = userService.getOne(userData.getId());
        user.init(userData);
        user.getRoles().clear();
        user.getRoles().addAll(userData.getRoles().stream().map(roleService::findByName).collect(Collectors.toList()));        ;
        userService.saveOrUpdate(user);
        return "redirect:/users";
    }

    @GetMapping("/roles")
    public String showAllRoles(
            Model model
    ){
        model.addAttribute("roles", roleService.getAllRoles());
        return "roles";
    }

    @GetMapping("/roles/add")
    public String addRole(
            Model model
    ){
        model.addAttribute("newRole", new Role());
        return "form_for_role";
    }

    @PostMapping("/roles/add")
    public String addRole(
            @ModelAttribute Role role
    ){
        roleService.saveOrUpdate(role);
        return"redirect:/roles";
    }
}
