package com.example.spring.todolist.service;


import com.example.spring.todolist.domain.User;
import com.example.spring.todolist.dto.UserCreateFormDTO;
import com.example.spring.todolist.dto.UserResponseFormDTO;
import com.example.spring.todolist.repository.UserRepository;
import com.example.spring.todolist.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public ResponseEntity<UserResponseFormDTO> create(UserCreateFormDTO userCreateFormDTO){

        // blank
        if(userCreateFormDTO.getUser_id().isBlank() || userCreateFormDTO.getPassword().isBlank()){
            return new ResponseEntity<>(new UserResponseFormDTO("데이터가 존재하지 않습니다.") , HttpStatus.BAD_REQUEST);
        }



        // Success
        Optional<User>member = Optional.ofNullable(userRepository.findByUser_id(userCreateFormDTO.getUser_id()));

        if(member.isEmpty()){   // 아이디 가입이 가능할 경우
            User newUser = User.builder().
                    user_id(userCreateFormDTO.getUser_id()).
                    password(passwordEncoder.encode(userCreateFormDTO.getPassword())).
                    build();
            userRepository.save(newUser);
            return new ResponseEntity<>(new UserResponseFormDTO("회원가입에 성공하였습니다") , HttpStatus.OK);
        }else{  // 불가능 할 경우 = 이미 존재하는 아이디
            return new ResponseEntity<>(new UserResponseFormDTO("이미 존재하는 아이디 입니다") , HttpStatus.CONFLICT);  // 이미 존재할 때는 CONFLICT를 응답으로
        }


    }
}
