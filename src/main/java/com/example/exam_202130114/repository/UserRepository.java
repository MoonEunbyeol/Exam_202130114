package com.example.exam_202130114.repository;

import com.example.exam_202130114.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUid(String uid);

    List<User> findAllByOrderByNameAsc();
}
