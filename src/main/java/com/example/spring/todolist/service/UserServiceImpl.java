package com.example.spring.todolist.service;


import com.example.spring.todolist.domain.LoginInfo;
import com.example.spring.todolist.domain.User;
import com.example.spring.todolist.dto.LoginFormDTO;
import com.example.spring.todolist.dto.UserCreateFormDTO;
import com.example.spring.todolist.dto.UserResponseFormDTO;
import com.example.spring.todolist.repository.LoginRepository;
import com.example.spring.todolist.repository.UserRepository;
import com.example.spring.todolist.service.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final LoginRepository loginRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  // 암호 데이터 복호화 , 매칭 관련 여부 확인




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
                    user_id(userCreateFormDTO.getUser_id()).    // 클라이언트에서 받은 아이디
                    password(passwordEncoder.encode(userCreateFormDTO.getPassword())).  // 클라이언트에서 받은 비밀번호 -> 암호화
                    build();
            userRepository.save(newUser);
            return new ResponseEntity<>(new UserResponseFormDTO("회원가입에 성공하였습니다") , HttpStatus.OK);
        }else{  // 불가능 할 경우 = 이미 존재하는 아이디
            return new ResponseEntity<>(new UserResponseFormDTO("이미 존재하는 아이디 입니다") , HttpStatus.CONFLICT);  // 이미 존재할 때는 CONFLICT를 응답으로
        }
    }


    @Override
    public ResponseEntity<UserResponseFormDTO> login(LoginFormDTO loginFormDTO , HttpSession session){
        // blank
        if(loginFormDTO.getUser_id().isBlank() || loginFormDTO.getPassword().isBlank()){
            return new ResponseEntity<>(new UserResponseFormDTO("데이터가 존재하지 않습니다.") , HttpStatus.BAD_REQUEST);
        }

        //Success
        User member = userRepository.findByUser_id(loginFormDTO.getUser_id());

        if(member == null){
            return new ResponseEntity<>(new UserResponseFormDTO("존재하지 않은 아이디 입니다.") , HttpStatus.NOT_FOUND);
        }else{
            if(passwordEncoder.matches(loginFormDTO.getPassword() , member.getPassword())){
                LoginInfo info = loginRepository.findByUser(member);
                if(info != null){
                    return new ResponseEntity<>(new UserResponseFormDTO("이미 다른기기에서 로그인중입니다") , HttpStatus.FORBIDDEN);
                }

                String sessionKey = UUID.randomUUID().toString();
                session.setAttribute("SESSION_KEY" , sessionKey);
                LoginInfo loginInfo = LoginInfo.builder().
                        sessionKey(passwordEncoder.encode(sessionKey)).
                        user(member).
                        build();
                loginRepository.save(loginInfo);
                return new ResponseEntity<>(new UserResponseFormDTO("로그인에 성공하였습니다.") ,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new UserResponseFormDTO("아이디와 비밀번호를 확인해주세요"),HttpStatus.UNAUTHORIZED);
            }
        }


    }
}
