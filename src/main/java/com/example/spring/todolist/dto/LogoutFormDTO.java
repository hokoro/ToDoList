package com.example.spring.todolist.dto;

import lombok.*;

@Getter
@Setter
public class LogoutFormDTO {
    private String sessionKey;
    private String user_id;
}
