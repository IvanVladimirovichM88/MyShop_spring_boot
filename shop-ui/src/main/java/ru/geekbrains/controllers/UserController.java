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


    @GetMapping("/detail")
    public String showUserDetail(
            Model model
    ){
        return "form/user_detail_form";
    }

    @PostMapping("/detail")
    public String updateUserDetail(
            @ModelAttribute UserData userData,
            Principal principal
    ){
        userService.update(userData,principal);
        return "redirect:/user/detail";
    }

    @GetMapping("/registration")
    public String showUserRegistrationForm(
            Model model
    ){
        return "form/user_registration_form";
    }

    @PostMapping("/registration")
    public String saveUser(
            @ModelAttribute UserData userData
    ){
        User user = userService.createOrUpdate(userData);
        userService.authenticateUser(user);
        return "redirect:/";
    }

}
