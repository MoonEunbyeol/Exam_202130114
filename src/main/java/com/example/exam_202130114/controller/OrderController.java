package com.example.exam_202130114.controller;

import com.example.exam_202130114.dto.OrderDto;
import com.example.exam_202130114.dto.OrderResponseDto;
import com.example.exam_202130114.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<OrderResponseDto> insertOrder(@RequestBody OrderDto orderDto) {
        OrderResponseDto orderResponseDto = orderService.insertOrder(orderDto);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.listAll());
    }

    @GetMapping("/listByUserId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> listByUserId(String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.listByUserId(userId));
    }

    @GetMapping("/listByProductId")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderResponseDto>> listByProductId(String productId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.listByProductId(productId));
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<OrderResponseDto> selectOrderById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.selectOrderById(id));
    }
}
