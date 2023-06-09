package com.example.exam_202130114.dao.impl;

import com.example.exam_202130114.dao.OrderDAO;
import com.example.exam_202130114.entity.Order;
import com.example.exam_202130114.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAOImpl implements OrderDAO {

    private final OrderRepository orderRepository;

    public OrderDAOImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order insertOrder(Order order) {
        Order saveOrder = orderRepository.save(order);
        return saveOrder;
    }

    @Override
    public List<Order> listAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> listByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> listByProductId(String productId) {
        return orderRepository.findByProductId(productId);
    }

    @Override
    public Order selectOrderById(Long id) {
        return orderRepository.findOrderById(id);
    }
}
