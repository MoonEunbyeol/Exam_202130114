package com.example.ch14.repository;

import com.example.ch14.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name, Sort sort); // sort 이용
//    Product findByName(String name);

    Long countByPrice(int price);

    boolean existsByNumber(Long number); // 존재하는지

    Product findByNameAndPrice(String name, int price);

    List<Product> findByNameOrderByPriceDesc(String name); // 정렬 조건이 price 오름차순(Asc)

    List<Product> findAllByOrderByPriceAsc(); // 기준이 없어도 By까지 작성

//    @Query("SELECT p FROM Product AS p WHERE p.stock = ?1") // ?1은 첫번째 파라미터를 뜻함
    @Query("SELECT p FROM Product AS p WHERE p.stock = :stock") 
    List<Product> listByStock(@Param("stock") int stock); // 이름이랑 매핑0000000000000000

}
