package com.example.exam_202130114.service.impl;

import com.example.exam_202130114.dao.OrderDAO;
import com.example.exam_202130114.dto.OrderDto;
import com.example.exam_202130114.dto.OrderResponseDto;
import com.example.exam_202130114.entity.Order;
import com.example.exam_202130114.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public OrderResponseDto insertOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setProductId(orderDto.getProductId());
        order.setProductName(orderDto.getProductName());
        order.setUserId(orderDto.getUserId());
        order.setUserName(orderDto.getUserName());
        order.setPrice(orderDto.getPrice());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        Order saveOrder = orderDAO.insertOrder(order);

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(saveOrder.getId());
        orderResponseDto.setProductId(saveOrder.getProductId());
        orderResponseDto.setProductName(saveOrder.getProductName());
        orderResponseDto.setUserId(saveOrder.getUserId());
        orderResponseDto.setUserName(saveOrder.getUserName());
        orderResponseDto.setPrice(saveOrder.getPrice());

        return orderResponseDto;
    }

    @Override
    public List<OrderResponseDto> listAll() {
        List<Order> orders = orderDAO.listAll();
        List<OrderResponseDto> orderResponseDtoList = orders.stream().map(
                OrderResponseDto::new).collect(Collectors.toList());
        return orderResponseDtoList;
    }

    @Override
    public List<OrderResponseDto> listByUserId(String userId) {
        List<Order> orders = orderDAO.listByUserId(userId);
        List<OrderResponseDto> orderResponseDtoList = orders.stream().map(
                order -> new OrderResponseDto(order)).collect(Collectors.toList());
        return orderResponseDtoList;
    }

    @Override
    public List<OrderResponseDto> listByProductId(String productId) {
        List<Order> orders = orderDAO.listByProductId(productId);
        List<OrderResponseDto> orderResponseDtoList = orders.stream().map(
                order -> new OrderResponseDto(order)).collect(Collectors.toList());
        return orderResponseDtoList;
    }

    @Override
    public OrderResponseDto selectOrderById(Long id) {
        Order order = orderDAO.selectOrderById(id);
        return new OrderResponseDto(order);
    }
}
