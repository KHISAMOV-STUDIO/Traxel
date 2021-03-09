package ru.traxel.traxel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.traxel.traxel.models.UserDTO;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String register(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register/register";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/login")
    public String login() {
        return "register/login";
    }

    @PostMapping("/login")
    public String loginUser() {
        return "redirect:/";
    }

    @PostMapping("/register")
    public String registerNewUser() {
        return "redirect:/";
    }
}
