package com.project03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/main")
    public String main() {
        System.out.println("zzzzz?");
        return "home/main";
    }
}
