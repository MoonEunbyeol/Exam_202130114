package com.example.exam_202130114.dao;

import com.example.exam_202130114.entity.User;

import java.util.List;

public interface UserDAO {

    List<User> listAll();

    List<User> listOrderByName();
}
