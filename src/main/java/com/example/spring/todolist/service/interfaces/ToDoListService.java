package com.example.spring.todolist.service.interfaces;

import com.example.spring.todolist.dto.ToDoListFormDTO;
import com.example.spring.todolist.dto.ToDoListResponseFormDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;


public interface ToDoListService {
    ResponseEntity<ToDoListResponseFormDTO> create(ToDoListFormDTO toDoListFormDTO , HttpSession session);
}
