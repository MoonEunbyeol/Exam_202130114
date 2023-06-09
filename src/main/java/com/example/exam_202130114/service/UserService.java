package com.example.exam_202130114.service;

import com.example.exam_202130114.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> listAll();
    List<UserResponseDto> listOrderByName();
}
