package ru.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.geekbrains.data.UserData;
import ru.geekbrains.persists.entities.User;
import ru.geekbrains.services.UserServiceShopUI;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserServiceShopUI userService;

    public UserController(UserServiceShopUI userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String showUserRegistrationForm(
            Model model
    ){
        model.addAttribute("userData", new UserData());
        return "userForm";
    }

    @PostMapping("/registration")
    public String saveUser(
            @ModelAttribute UserData userData
    ){
        User user = userService.createOrUpdate(userData);
        userService.authenticateUser(user );
        return "redirect:/";
    }

}
