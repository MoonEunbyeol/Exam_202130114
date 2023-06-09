package com.example.exam_202130114.service;

import com.example.exam_202130114.dto.BoardDto;
import com.example.exam_202130114.dto.BoardResponseDto;

import java.util.List;

public interface BoardService {
    BoardResponseDto insertBoard(BoardDto boardDto);
    BoardResponseDto updateBoard(Long id, String title, String contents, String userId) throws Exception;
    void deleteBoard(Long id, String userId) throws Exception;

    List<BoardResponseDto> listAll();
    List<BoardResponseDto> listOrderByCreatedAt();
    List<BoardResponseDto> listByUserId(String userId);
    BoardResponseDto selectBoardById(Long id);
}
