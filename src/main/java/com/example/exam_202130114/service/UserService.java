package com.example.ch14_test.service;

import com.example.ch14_test.dto.UserDto;
import com.example.ch14_test.dto.UserResponseDto;

public interface UserService {

    UserResponseDto getUser(Long id);
    UserResponseDto saveUser(UserDto userDto);
    UserResponseDto changeUserName(Long id, String name) throws Exception;
    void deleteUser(Long id) throws Exception;
}
