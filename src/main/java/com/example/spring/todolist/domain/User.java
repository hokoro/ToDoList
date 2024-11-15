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
@Table(name="user")
public class User {
    @Id
    @Column(nullable=false,unique = true,name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false ,unique = true, name="user_id")
    private String user_id;

    @Column(nullable = false , name="password")
    private String password;

    @OneToOne(mappedBy = "user")
    private LoginInfo loginInfo; // 양방향 관계를 위해 추가 (선택 사항)

}
