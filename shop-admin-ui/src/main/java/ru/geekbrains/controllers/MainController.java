package ru.geekbrains.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class MainController {

    @GetMapping
    public String indexPage(
            Model model
    ){
        model.addAttribute("activePage", "None");
        return "index";
    }

}
