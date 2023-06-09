package com.example.exam_202130114.service;

import com.example.exam_202130114.dto.ProductDto;
import com.example.exam_202130114.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto saveProduct(ProductDto productDto);
    ProductResponseDto updateProduct(Long number, String name, int price, int stock) throws Exception;
    void deleteProduct(Long number) throws Exception;

    List<ProductResponseDto> listAll();
    List<ProductResponseDto> listOrderByPrice();
    List<ProductResponseDto> listProductByName(String name);
    ProductResponseDto selectProductByNumber(Long number);
}
