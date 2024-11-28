package com.example.spring.todolist.dto;

import lombok.*;


@Getter
@Setter
public class ToDoListFormDTO {
    private String todo;
    private String user_id;
    private String sessionKey;
}
