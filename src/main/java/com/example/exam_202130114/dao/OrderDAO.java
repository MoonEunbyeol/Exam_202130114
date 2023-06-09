package com.example.exam_202130114.dao;

import com.example.exam_202130114.entity.Order;

import java.util.List;

public interface OrderDAO {
    Order insertOrder(Order order);
    List<Order> listAll();
    List<Order> listByUserId(String userId);
    List<Order> listByProductId(String productId);
    Order selectOrderById(Long id);
}
