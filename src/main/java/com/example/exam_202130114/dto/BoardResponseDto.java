package com.example.exam_202130114.dto;

import com.example.exam_202130114.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String userId;
    private String userName;

    public BoardResponseDto() {}

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
    }
}
