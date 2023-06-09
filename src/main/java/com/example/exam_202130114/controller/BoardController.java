package com.example.exam_202130114.controller;

import com.example.exam_202130114.dto.BoardDto;
import com.example.exam_202130114.dto.BoardResponseDto;
import com.example.exam_202130114.dto.ChangeBoardDto;
import com.example.exam_202130114.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<BoardResponseDto> insertBoard(@RequestBody BoardDto boardDto) {
        BoardResponseDto boardResponseDto = boardService.insertBoard(boardDto);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    @PutMapping()
    public ResponseEntity<BoardResponseDto> changeBoard(@RequestBody ChangeBoardDto changeBoardDto) throws Exception {
        BoardResponseDto boardResponseDto = boardService
                .updateBoard(changeBoardDto.getId(),
                        changeBoardDto.getTitle(),
                        changeBoardDto.getContents(),
                        changeBoardDto.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBoard(Long id, String userId) throws Exception {
        boardService.deleteBoard(id, userId);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardResponseDto>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.listAll());
    }

    @GetMapping("/listOrderByCreatedAt")
    public ResponseEntity<List<BoardResponseDto>> listOrderByCreatedAt() {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.listOrderByCreatedAt());
    }

    @GetMapping("/byUserId")
    public ResponseEntity<List<BoardResponseDto>> listByUserId(String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.listByUserId(userId));
    }

    @GetMapping("/")
    public ResponseEntity<BoardResponseDto> selectBoardById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.selectBoardById(id));
    }
}
