package ru.traxel.traxel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.traxel.traxel.dao.CustomerDAO;
import ru.traxel.traxel.models.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    final CustomerDAO customerDAO;

    @Autowired
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("customers", customerDAO.list());
        return "customer/index";
    }

    @GetMapping("/index")
    public String customer(Model model) {
        model.addAttribute("customers", customerDAO.list());
        return "customer/index";
    }


    @GetMapping("/new")
    public String newCustomer(@ModelAttribute("customer") Customer a) {
        return "customer/new";
    }

    @PostMapping("/new")
    public String create(@RequestParam(value="email", required = true) String email,
                         @RequestParam(value="name", required = true) String name) {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setName(name);
        customerDAO.save(customer);
        return "redirect:/customer/";
    }

}
