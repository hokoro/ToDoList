package com.example.spring.todolist.repository;

import com.example.spring.todolist.domain.LoginInfo;
import com.example.spring.todolist.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginRepository extends JpaRepository<LoginInfo, Long> {

    @Query("SELECT u FROM LoginInfo u WHERE u.user = :user")
    LoginInfo findByUser(@Param("user")User user);
}
