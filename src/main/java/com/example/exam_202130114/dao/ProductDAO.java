package com.example.exam_202130114.dao;

import com.example.exam_202130114.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {

    Product insertProduct(Product product);
    Product updateProduct(Long number, String name, int price, int stock) throws Exception;
    void deleteProduct(Long number) throws Exception;

    List<Product> listAll();
    List<Product> listOrderByPrice();
    List<Product> listProductByName(String name);
    Product selectProductByNumber(Long number);
}
