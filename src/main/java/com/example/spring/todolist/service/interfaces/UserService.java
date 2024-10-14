package com.example.spring.todolist.service.interfaces;

import com.example.spring.todolist.dto.UserCreateFormDTO;
import com.example.spring.todolist.dto.UserResponseFormDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserResponseFormDTO> create(UserCreateFormDTO userCreateFormDTO);
}
