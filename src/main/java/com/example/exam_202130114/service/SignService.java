package com.example.exam_202130114.service;

import com.example.exam_202130114.dto.SignInResultDto;
import com.example.exam_202130114.dto.SignUpResultDto;

public interface SignService {

    SignUpResultDto signUp(String id, String password, String name, String email, String role);

    SignInResultDto signIn(String id, String password) throws RuntimeException;
}
