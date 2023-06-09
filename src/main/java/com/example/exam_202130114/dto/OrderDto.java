package com.example.exam_202130114.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {

    private String productId;
    private String productName;
    private String userId;
    private String userName;
    private int price;

}
