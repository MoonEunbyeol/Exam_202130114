package com.example.ch14.dao;

import com.example.ch14.entity.Product;

import java.util.List;

public interface ProductDAO {

    Product insertProduct(Product product);

    Product selectProduct(Long number);

    List<Product> selectProductByName(String name);

    Product selectProductByNameAndPrice(String name, int price);

    Long countByPrice(int price);

    boolean existsByNumber(Long number);

    List<Product> listProductByName(String name);

    List<Product> listAll(); // 교수님은 listProduct로 진행

    List<Product> allProduct();

    List<Product> listByStock(int stock);

    Product updateProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;

}
