package com.wideedu.ordercartspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContentController {

    @GetMapping("/login")
    public String login() {
        return "custom_login";
    }

    @GetMapping("/product-catalog")
    public String showCatalog(@RequestParam(defaultValue = "1") int page, Model model) {
        model.addAttribute("currentPage", page);
        return "catalog";
    }

    @GetMapping("/order-cart")
    public String showCart() {
        return "cart";
    }

    @GetMapping("/register")
    public String register() {
        return "registration";
    }
}
