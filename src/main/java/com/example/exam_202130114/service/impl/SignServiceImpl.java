package com.example.exam_202130114.service.impl;

import com.example.exam_202130114.config.security.JwtTokenProvider;
import com.example.exam_202130114.dto.CommonResponse;
import com.example.exam_202130114.dto.SignInResultDto;
import com.example.exam_202130114.dto.SignUpResultDto;
import com.example.exam_202130114.entity.User;
import com.example.exam_202130114.repository.UserRepository;
import com.example.exam_202130114.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SignServiceImpl implements SignService {

    public UserRepository userRepository;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignUpResultDto signUp(String id, String password, String name, String email, String role) {
        System.out.println("[signUp] 회원가입");
        User user;
        if (role.equalsIgnoreCase("admin")) { // 대소문자 안가리고 admin 체크
            user = User.builder().uid(id).name(name).email(email)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_ADMIN")).build();
        } else {
            user = User.builder().uid(id).name(name).email(email)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_USER")).build();
        }
        User savedUser = userRepository.save(user);
        SignUpResultDto signUpResultDto = new SignUpResultDto();
        if (!savedUser.getName().isEmpty()) { // DB에 등록된 유저의 네임이 비어있지 않으면 정상적으로 등록
            setSuccessResult(signUpResultDto);
        } else {
            setFailResult(signUpResultDto);
        }
        return signUpResultDto;
    }

    private void setSuccessResult(SignUpResultDto signUpResultDto) {
        signUpResultDto.setSuccess(true);
        signUpResultDto.setCode(CommonResponse.SUCCESS.getCode());
        signUpResultDto.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDto signUpResultDto) {
        signUpResultDto.setSuccess(false);
        signUpResultDto.setCode(CommonResponse.FAIL.getCode());
        signUpResultDto.setMsg(CommonResponse.FAIL.getMsg());
    }

    @Override
    public SignInResultDto signIn(String id, String password) throws RuntimeException {
        User user = userRepository.getByUid(id);
        if (!passwordEncoder.matches(password, user.getPassword())) { // 패스워드 상이할 시
            throw new RuntimeException(); // 에러 발생해서 Fail 따로 안해도 됨
        }
        String token = jwtTokenProvider.createToken(String.valueOf(user.getUid()), user.getRoles());
        SignInResultDto signInResultDto = SignInResultDto.builder().token(token).build();
        setSuccessResult(signInResultDto);
        return signInResultDto;
    }
}
