package com.example.exam_202130114.dao.impl;

import com.example.exam_202130114.dao.OrderDAO;
import com.example.exam_202130114.entity.Order;
import com.example.exam_202130114.entity.Product;
import com.example.exam_202130114.repository.OrderRepository;
import com.example.exam_202130114.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDAOImpl implements OrderDAO {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public OrderDAOImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order insertOrder(Order order) throws Exception {
        Product productByName = productRepository.findProductByName(order.getProductName());
//        order.getProductName().equals(productByName);
        if (productByName.getStock() > 0) {
            productByName.setStock(productByName.getStock() - 1);
            productRepository.save(productByName);
            Order saveOrder = orderRepository.save(order);
            return saveOrder;
        } else {
            System.out.println(">> 재고 부족");
            throw new Exception();
        }
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
