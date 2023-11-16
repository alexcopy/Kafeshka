package com.kafeshka.KafeshkaRS.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("message", "Privet NiNa Welcome to my KaFeSHka RS!");
        return "index";
    }
}
