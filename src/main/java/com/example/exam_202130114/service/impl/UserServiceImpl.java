package com.example.exam_202130114.service.impl;

import com.example.exam_202130114.dao.UserDAO;
import com.example.exam_202130114.dto.UserResponseDto;
import com.example.exam_202130114.entity.User;
import com.example.exam_202130114.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<UserResponseDto> listAll() {
        List<User> users = userDAO.listAll();
        List<UserResponseDto> userResponseDtoList = users.stream().map(
                UserResponseDto::new).collect(Collectors.toList());
        return userResponseDtoList;
    }

    @Override
    public List<UserResponseDto> listOrderByName() {
        List<User> users = userDAO.listOrderByName();
        List<UserResponseDto> userResponseDtoList = users.stream().map(
                product -> new UserResponseDto(product)).collect(Collectors.toList());
        return userResponseDtoList;
    }
}
