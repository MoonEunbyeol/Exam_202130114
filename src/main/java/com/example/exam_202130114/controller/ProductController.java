package com.example.exam_202130114.controller;

import com.example.exam_202130114.dto.ChangeProductDto;
import com.example.exam_202130114.dto.ProductDto;
import com.example.exam_202130114.dto.ProductResponseDto;
import com.example.exam_202130114.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductResponseDto> changeProduct(@RequestBody ChangeProductDto changeProductDto) throws Exception {
        ProductResponseDto productResponseDto = productService
                .updateProduct(changeProductDto.getNumber(),
                        changeProductDto.getName(),
                        changeProductDto.getPrice(),
                        changeProductDto.getStock());
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDto>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.listAll());
    }

    @GetMapping("/listOrderByPrice")
    public ResponseEntity<List<ProductResponseDto>> listOrderByPrice() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.listOrderByPrice());
    }

    @GetMapping("/byName")
    public ResponseEntity<List<ProductResponseDto>> listOrderByPrice(String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.listProductByName(name));
    }

    @GetMapping("/")
    public ResponseEntity<ProductResponseDto> selectProductById(Long number) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.selectProductByNumber(number));
    }

}
