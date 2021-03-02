package ru.traxel.traxel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.traxel.traxel.models.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String register(WebRequest request, Model model){
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register/register";
    }

    public ModelAndView registerUser(@ModelAttribute("user") @Valid UserDTO user,
                                     HttpServletRequest request, Errors errors) {

    }
}
