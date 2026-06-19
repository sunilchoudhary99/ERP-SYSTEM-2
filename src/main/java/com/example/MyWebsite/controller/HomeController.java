package com.example.MyWebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/user.html";
    }

//    @GetMapping("/")
//    public String hom() {
//        return "redirect:/admin.html";
//    }
}