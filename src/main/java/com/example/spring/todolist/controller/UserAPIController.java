package com.example.spring.todolist.controller;

import com.example.spring.todolist.dto.UserCreateFormDTO;
import com.example.spring.todolist.dto.UserResponseFormDTO;
import com.example.spring.todolist.repository.UserRepository;
import com.example.spring.todolist.service.interfaces.UserService;
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
    private final UserRepository userRepository;

    @PostMapping("/user/create")
    public ResponseEntity<UserResponseFormDTO> Create(@RequestBody UserCreateFormDTO userCreateFormDTO){
        return userService.create(userCreateFormDTO);
    }


}
