package com.example.spring.todolist.service;

import com.example.spring.todolist.domain.LoginInfo;
import com.example.spring.todolist.domain.ToDoList;
import com.example.spring.todolist.domain.User;
import com.example.spring.todolist.dto.ToDoListFormDTO;
import com.example.spring.todolist.dto.ToDoListResponseFormDTO;
import com.example.spring.todolist.repository.LoginRepository;
import com.example.spring.todolist.repository.ToDoRepository;
import com.example.spring.todolist.repository.UserRepository;
import com.example.spring.todolist.service.interfaces.ToDoListService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Service
@Transactional
@RequiredArgsConstructor
public class ToDoListServiceImpl implements ToDoListService {

    private final LoginRepository loginRepository;

    private final UserRepository userRepository;

    private final ToDoRepository toDoRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("Asia/Seoul"));

    @Override
    public ResponseEntity<ToDoListResponseFormDTO> create(ToDoListFormDTO toDoListFormDTO , HttpSession session){
        String user_id = toDoListFormDTO.getUser_id();
        String sessionkey = toDoListFormDTO.getSessionKey();
        String todo = toDoListFormDTO.getTodo();

        if(user_id.isBlank() || sessionkey.isBlank() || todo.isBlank()){    // 데이터 존재 유무
            return new ResponseEntity<>(new ToDoListResponseFormDTO("데이터가 존재하지 않습니다.",null) , HttpStatus.BAD_REQUEST);
        }

        // 회원 정보 존재 여부 확인
        User member = userRepository.findByUser_id(user_id);

        if(member == null){
            return new ResponseEntity<>(new ToDoListResponseFormDTO("회원 정보가 존재하지 않습니다." , null) , HttpStatus.NOT_FOUND);
        } else{
            LoginInfo loginInfo = loginRepository.findByUser(member);
            if(loginInfo == null){  // 로그인 정보가 없을떄
                return new ResponseEntity<>(new ToDoListResponseFormDTO("로그인 정보가 없습니다." , null) , HttpStatus.NOT_FOUND);
            }else{
                if(passwordEncoder.matches(sessionkey,loginInfo.getSessionKey()) && session.getAttribute(user_id).equals(sessionkey)){    // 정상 실행
                    ToDoList toDoList = ToDoList.builder().
                                                 todo(todo).
                                                 user(member).
                                                 build();
                    toDoRepository.save(toDoList);
                    return new ResponseEntity<>(new ToDoListResponseFormDTO("리스트에 등록 되었습니다" , toDoList.getCreatedAt().format(dateTimeFormatter)),HttpStatus.OK);
                }else{  // 로그인 정보가 일치하지 않을 때
                    return new ResponseEntity<>(new ToDoListResponseFormDTO("로그인 정보가 일치하지 않습니다.",null),HttpStatus.UNAUTHORIZED);
                }
            }

        }
    }


}



