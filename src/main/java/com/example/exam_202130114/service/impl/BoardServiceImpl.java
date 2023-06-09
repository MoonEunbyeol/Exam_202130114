package com.example.exam_202130114.service.impl;

import com.example.exam_202130114.dao.BoardDAO;
import com.example.exam_202130114.dto.BoardDto;
import com.example.exam_202130114.dto.BoardResponseDto;
import com.example.exam_202130114.entity.Board;
import com.example.exam_202130114.service.BoardService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;

    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public BoardResponseDto insertBoard(BoardDto boardDto) {
        Board board = new Board();
        board.setTitle(boardDto.getTitle());
        board.setContents(boardDto.getContents());
        board.setUserId(boardDto.getUserId());
        board.setUserName(boardDto.getUserName());
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());

        Board saveBoard = boardDAO.insertBoard(board);

        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(saveBoard.getId());
        boardResponseDto.setTitle(saveBoard.getTitle());
        boardResponseDto.setContents(saveBoard.getContents());
        boardResponseDto.setUserId(saveBoard.getUserId());
        boardResponseDto.setUserName(saveBoard.getUserName());

        return boardResponseDto;

    }

    @Override
    public BoardResponseDto updateBoard(Long id, String title, String contents, String userId) throws Exception {
        Board changeBoard = boardDAO.updateBoard(id, title, contents, userId);
        return new BoardResponseDto(changeBoard);
    }

    @Override
    public void deleteBoard(Long id, String userId) throws Exception {
        boardDAO.deleteBoard(id, userId);
    }

    @Override
    public List<BoardResponseDto> listAll() {
        List<Board> boards = boardDAO.listAll();
        List<BoardResponseDto> boardsResponseDtoList = boards.stream().map(
                BoardResponseDto::new).collect(Collectors.toList());
        return boardsResponseDtoList;
    }

    @Override
    public List<BoardResponseDto> listOrderByCreatedAt() {
        List<Board> boards = boardDAO.listOrderByCreatedAt();
        List<BoardResponseDto> boardsResponseDtoList = boards.stream().map(
                board -> new BoardResponseDto(board)).collect(Collectors.toList());
        return boardsResponseDtoList;
    }

    @Override
    public List<BoardResponseDto> listByUserId(String userId) {
        List<Board> boards = boardDAO.listByUserId(userId);
        List<BoardResponseDto> boardsResponseDtoList = boards.stream().map(
                board -> new BoardResponseDto(board)).collect(Collectors.toList());
        return boardsResponseDtoList;
    }

    @Override
    public BoardResponseDto selectBoardById(Long id) {
        Board boards = boardDAO.selectBoardById(id);
        return new BoardResponseDto(boards);
    }
}
