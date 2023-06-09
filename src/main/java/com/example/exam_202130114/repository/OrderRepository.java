package com.example.exam_202130114.repository;

import com.example.exam_202130114.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(String userId);
    List<Order> findByProductId(String productId);
    Order findOrderById(Long id);
}
