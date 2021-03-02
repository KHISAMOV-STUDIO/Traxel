package ru.traxel.traxel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogueController {

    @GetMapping("/catalogue")
    public String catalogue() {
        return "catalogue/catalog";
    }
}
