package com.example.spring.todolist.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LogoutResponseFormDTO {
    // 로그아웃 확인 메시지
    private String message;
}
