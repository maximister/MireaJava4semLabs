package ru.mirea.maximister.task14.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomePageController {
    @GetMapping
    public String getHomePage() {
        return "index";
    }
}