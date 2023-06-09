package com.example.exam_202130114.service;


import com.example.exam_202130114.dto.OrderDto;
import com.example.exam_202130114.dto.OrderResponseDto;
import com.example.exam_202130114.entity.Order;

import java.util.List;

public interface OrderService {
    OrderResponseDto insertOrder(OrderDto orderDto);
    List<OrderResponseDto> listAll();
    List<OrderResponseDto> listByUserId(String userId);
    List<OrderResponseDto> listByProductId(String productId);
    OrderResponseDto selectOrderById(Long id);
}
