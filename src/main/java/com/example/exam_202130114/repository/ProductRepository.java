package com.example.exam_202130114.repository;

import com.example.exam_202130114.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByPriceDesc();

    List<Product> findByName(String name);

    Product findProductByNumber(Long number);
}
