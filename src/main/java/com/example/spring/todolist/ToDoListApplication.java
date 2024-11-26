package com.example.spring.todolist;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing  // JPA Auditing 활성화 해야 -> Created_at 값을 설정
public class ToDoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoListApplication.class, args);
        //LocalDateTime localDateTime = LocalDateTime.now();
        //System.out.println("현재 시간: " + localDateTime);
    }

    @PostConstruct  // spring이  초기화된 후 실행되는 메서드의 한 종류
    public void init(){
        // timezone 설정
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));   // 서버 시간대 설정
    }
}
