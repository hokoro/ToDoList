package com.example.spring.todolist.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "logininfo")
public class LoginInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_key", nullable = false)
    private String sessionKey;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true) // 외래키 설정
    private User user;

}
