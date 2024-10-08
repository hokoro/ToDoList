        package com.example.spring.todolist.controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;

        @Controller
        public class helloController {
            @GetMapping("/hello")
            public String hello(Model model){
                model.addAttribute("name","이름");
                model.addAttribute("img","images/img.jpg");
                return "hello";
            }
        }
