package com.example.exam_202130114.dao;

import com.example.exam_202130114.entity.Board;

import java.util.List;

public interface BoardDAO {

    Board insertBoard(Board board);
    Board updateBoard(Long id, String title, String contents, String userId) throws Exception;
    void deleteBoard(Long id, String userId) throws Exception;

    List<Board> listAll();
    List<Board> listOrderByCreatedAt();
    List<Board> listByUserId(String userId);
    Board selectBoardById(Long id);
}
