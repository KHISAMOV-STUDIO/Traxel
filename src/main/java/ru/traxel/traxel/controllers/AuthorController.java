package ru.traxel.traxel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.traxel.traxel.dao.AuthorDAO;
import ru.traxel.traxel.models.Author;

@Controller
@RequestMapping("/author")
public class AuthorController {
    final AuthorDAO authorDAO;

    @Autowired
    public AuthorController(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("authors", authorDAO.list());
        return "author/index";
    }

    @GetMapping("/index")
    public String author(Model model) {
        model.addAttribute("authors", authorDAO.list());
        return "author/index";
    }


    @GetMapping("/new")
    public String newAuthor(@ModelAttribute("author") Author a) {
        return "author/new";
    }

    @PostMapping("/new")
    public String create(@RequestParam(value="email", required = true) String email,
                         @RequestParam(value="name", required = true) String name) {
        Author author = new Author();
        author.setEmail(email);
        author.setName(name);
        authorDAO.save(author);
        return "redirect:/author/";
    }
}
