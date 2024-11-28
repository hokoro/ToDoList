package com.example.spring.todolist.repository;


import com.example.spring.todolist.domain.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDoList, Long> {

}
