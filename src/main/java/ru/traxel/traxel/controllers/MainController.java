package ru.traxel.traxel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String main() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}