package ru.traxel.traxel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.traxel.traxel.dao.CartDAO;
import ru.traxel.traxel.models.Cart;

@Controller
@RequestMapping("/cart")
public class CartController {

    final CartDAO cartDAO;

    @Autowired
    public CartController(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("carts", cartDAO.list());
        return "cart/index";
    }

    @GetMapping("/index")
    public String cart(Model model) {
        model.addAttribute("carts", cartDAO.list());
        return "cart/index";
    }


    @GetMapping("/new")
    public String newCart(@ModelAttribute("cart") Cart cart) {
        return "cart/new";
    }

    @PostMapping("/new")
    public String create(@RequestParam(value="customeremail", required = true) String email) {
        Cart cart = new Cart();
        cart.setCustomeremail(email);
        cartDAO.save(cart);
        return "redirect:/cart/";
    }
}
