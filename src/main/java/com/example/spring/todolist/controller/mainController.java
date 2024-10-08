package com.example.spring.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    @GetMapping("/Signin")
    public String Signin(Model model){
        return "signin";
    }

    @GetMapping("/Login")
    public String Login(Model model){
        return "login";
    }

}
