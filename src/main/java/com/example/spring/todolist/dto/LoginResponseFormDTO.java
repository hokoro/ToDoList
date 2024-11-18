package com.example.spring.todolist.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseFormDTO {
    private String message;
    private String token;
}
