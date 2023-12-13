package com.kafeshka.KafeshkaRS.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage(Model model) {

        return "index";
    }
    @GetMapping("/")
    public String indexPage(Model model) {

        return "index";
    }
}
