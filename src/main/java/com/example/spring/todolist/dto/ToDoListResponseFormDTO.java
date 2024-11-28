package com.example.spring.todolist.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ToDoListResponseFormDTO {
    private String message;           // 메세지
    private String localDateTime;    // 생성 시간
}


