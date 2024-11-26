package com.example.spring.todolist.domain;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "todolist")
public class ToDoList {
    @Id
    @Column(nullable=false , unique = true , name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 할일 목록
    @Column(nullable = false , unique = false , name="todo")
    private String todo;

    // 날짜 정보(생성 날짜)
    @CreatedDate
    @Column(updatable = false , nullable = false)
    private LocalDateTime createdAt;

    // 날짜 정보(수정 날짜)
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateAt;

    // 작성자 정보
    @ManyToOne(fetch = FetchType.LAZY)  // 여러 게시글이 한 사용자를 참조
    @JoinColumn(name="user_id", referencedColumnName = "id",nullable = false)   // name 명시적 이름 , referencedColumn = 참조할 컬럼의 이름
    private User user;

}
