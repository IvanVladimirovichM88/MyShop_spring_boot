package ru.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.data.UserData;
import ru.geekbrains.persists.entities.User;
import ru.geekbrains.services.RoleService;
import ru.geekbrains.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    public UserService userService;
    public RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(
            Model model
    ){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/add")
    public String showFormAddUser(
            Model model
    ){
        model.addAttribute("userData", new UserData());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "forms/form_add_user";
    }

    @PostMapping("/add")
    public String addUser(
            @ModelAttribute UserData userData
    ){
        userService.createOrUpdate(userData);
        return "redirect:/users";
    }

    @GetMapping("/edit/{idUser}")
    public String showFormEditUser(
            @PathVariable Long idUser,
            Model model
    ){
        User user = userService.getOne(idUser);
        model.addAttribute("userData", new UserData(user));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "forms/form_edit_user";
    }

    @PostMapping("/edit")
    public String editUser(
            @ModelAttribute UserData userData
    ){
        userService.createOrUpdate(userData);
        return "redirect:/users";
    }

}
