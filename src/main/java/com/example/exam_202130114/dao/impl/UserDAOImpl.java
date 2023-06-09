package com.example.exam_202130114.dao.impl;

import com.example.exam_202130114.dao.UserDAO;
import com.example.exam_202130114.entity.User;
import com.example.exam_202130114.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    private final UserRepository userRepository;

    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> listOrderByName() {
        return userRepository.findAllByOrderByNameAsc();
    }
}
