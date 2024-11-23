package com.example.spring.todolist.service.interfaces;

import com.example.spring.todolist.dto.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;

public interface UserService {

    // 계정 생성
    ResponseEntity<UserResponseFormDTO> create(UserCreateFormDTO userCreateFormDTO);

    // 로그인
    ResponseEntity<LoginResponseFormDTO> login(LoginFormDTO loginFormDTO , HttpSession session);

    // 로그아웃
    ResponseEntity<LogoutResponseFormDTO> logout(LogoutFormDTO logoutFormDTO , HttpSession session);

}
