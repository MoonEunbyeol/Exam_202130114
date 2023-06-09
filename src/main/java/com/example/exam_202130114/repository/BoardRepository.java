package com.example.exam_202130114.repository;

import com.example.exam_202130114.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByCreatedAtDesc();

    List<Board> findByUserId(String userId);

    Board findBoardById(Long id);
}
