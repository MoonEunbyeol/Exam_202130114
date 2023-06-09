package com.example.exam_202130114.controller;

import com.example.exam_202130114.dto.UserResponseDto;
import com.example.exam_202130114.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserResponseDto>> listAll() {
        List<UserResponseDto> userResponseDto = userService.listAll();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @GetMapping("/listOrderByName")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserResponseDto>> listOrderByName() {
        List<UserResponseDto> userResponseDto = userService.listOrderByName();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }
}
