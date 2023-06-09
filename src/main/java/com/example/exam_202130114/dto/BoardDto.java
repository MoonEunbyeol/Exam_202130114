package com.example.exam_202130114.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardDto {
    
    private String title;
    private String contents;
    private String userId;
    private String userName;
}
