package com.example.exam_202130114.service.impl;

import com.example.exam_202130114.dao.ProductDAO;
import com.example.exam_202130114.dto.ProductDto;
import com.example.exam_202130114.dto.ProductResponseDto;
import com.example.exam_202130114.entity.Product;
import com.example.exam_202130114.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product saveProduct = productDAO.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(saveProduct.getName());
        productResponseDto.setNumber(saveProduct.getNumber());
        productResponseDto.setPrice(saveProduct.getPrice());
        productResponseDto.setStock(saveProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(Long number, String name, int price, int stock) throws Exception {
        Product changeProduct = productDAO.updateProduct(number, name, price, stock);
        return new ProductResponseDto(changeProduct);
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }

    @Override
    public List<ProductResponseDto> listAll() {
        List<Product> products = productDAO.listAll();
        List<ProductResponseDto> productResponseDtoList = products.stream().map(
                ProductResponseDto::new).collect(Collectors.toList());
        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> listOrderByPrice() {
        List<Product> products = productDAO.listOrderByPrice();
        List<ProductResponseDto> productResponseDtoList = products.stream().map(
                product -> new ProductResponseDto(product)).collect(Collectors.toList());
        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> listProductByName(String name) {
        List<Product> products = productDAO.listProductByName(name); // 해당 부분만 변경
        List<ProductResponseDto> productResponseDtoList = products.stream().map(
                product -> new ProductResponseDto(product)).collect(Collectors.toList());
        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto selectProductByNumber(Long number) {
        Product product = productDAO.selectProductByNumber(number);
        return new ProductResponseDto(product);
    }
}
