package com.example.spring.todolist.repository;

import com.example.spring.todolist.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{


}
