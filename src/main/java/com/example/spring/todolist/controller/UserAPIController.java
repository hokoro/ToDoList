package com.example.spring.todolist.controller;

import com.example.spring.todolist.dto.*;
import com.example.spring.todolist.repository.UserRepository;
import com.example.spring.todolist.service.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserAPIController {
    private final UserService userService;

    @PostMapping("/user/create")
    public ResponseEntity<UserResponseFormDTO> Create(@RequestBody UserCreateFormDTO userCreateFormDTO){
        return userService.create(userCreateFormDTO);
    }

    @PostMapping("/user/login")
    public ResponseEntity<LoginResponseFormDTO> Login(@RequestBody LoginFormDTO loginFormDTO , HttpSession session){
        return userService.login(loginFormDTO , session);
    }

    @PostMapping("/user/logout")
    public ResponseEntity<LogoutResponseFormDTO> Logout(@RequestBody LogoutFormDTO logoutFormDTO , HttpSession session){
        return userService.logout(logoutFormDTO , session);
    }

}
