package com.example.spring.todolist.controller;


import com.example.spring.todolist.dto.ToDoListFormDTO;
import com.example.spring.todolist.dto.ToDoListResponseFormDTO;
import com.example.spring.todolist.service.interfaces.ToDoListService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ToDoListAPIController {
    private final ToDoListService toDoListService;

    @PostMapping("/todo/create")
    public ResponseEntity<ToDoListResponseFormDTO> Create(@RequestBody ToDoListFormDTO toDoListFormDTO , HttpSession session){
        return toDoListService.create(toDoListFormDTO , session);
    }

}
