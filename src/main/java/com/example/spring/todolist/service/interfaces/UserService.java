package com.example.spring.todolist.service.interfaces;

import com.example.spring.todolist.dto.LoginFormDTO;
import com.example.spring.todolist.dto.UserCreateFormDTO;
import com.example.spring.todolist.dto.UserResponseFormDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserResponseFormDTO> create(UserCreateFormDTO userCreateFormDTO);

    ResponseEntity<UserResponseFormDTO> login(LoginFormDTO loginFormDTO , HttpSession session);
}
