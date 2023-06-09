package com.example.exam_202130114.dao.impl;

import com.example.exam_202130114.dao.BoardDAO;
import com.example.exam_202130114.entity.Board;
import com.example.exam_202130114.repository.BoardRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class BoardDAOImpl implements BoardDAO {

    private final BoardRepository boardRepository;

    public BoardDAOImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board insertBoard(Board board) {
        Board saveBoard = boardRepository.save(board);
        return saveBoard;
    }

    @Override
    public Board updateBoard(Long id, String title, String contents, String userId) throws Exception {
        Board boardById = boardRepository.findBoardById(id);
        String checkUserId = boardById.getUserId();
        System.out.println("[checkUserId] : " + checkUserId);
        System.out.println("[inputUserId] : " + userId);

        if (checkUserId.equals(userId)) {
            Optional<Board> selectBoard = boardRepository.findById(id);
            Board updateBoard;
            if(selectBoard.isPresent()) {
                Board board = selectBoard.get();
                board.setTitle(title);
                board.setContents(contents);
                board.setUpdatedAt(LocalDateTime.now());
                updateBoard = boardRepository.save(board);
            } else {
                throw new Exception();
            }
            return updateBoard;
        } else {
            System.out.println(">> 작성된 userId와 미일치");
            throw new Exception(); // userId 미일치시
        }
    }

    @Override
    public void deleteBoard(Long id, String userId) throws Exception {
        Board boardById = boardRepository.findBoardById(id);
        String checkUserId = boardById.getUserId();

        if (checkUserId.equals(userId)) {
            Optional<Board> selectBoard = boardRepository.findById(id);

            if (selectBoard.isPresent()) {
                Board board = selectBoard.get();
                boardRepository.delete(board);
            } else {
                throw new Exception();
            }
        } else {
            System.out.println(">> 작성된 userId와 미일치");
            throw new Exception();
        }
    }

    @Override
    public List<Board> listAll() {
        return boardRepository.findAll();
    }

    @Override
    public List<Board> listOrderByCreatedAt() {
        return boardRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public List<Board> listByUserId(String userId) {
        return boardRepository.findByUserId(userId);
    }

    @Override
    public Board selectBoardById(Long id) {
        return boardRepository.findBoardById(id);
    }
}
