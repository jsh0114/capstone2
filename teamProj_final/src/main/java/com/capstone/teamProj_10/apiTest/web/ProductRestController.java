package com.capstone.teamProj_10.apiTest.web;


import com.capstone.teamProj_10.apiTest.item.Product;
import com.capstone.teamProj_10.apiTest.item.ProductRepository;
import com.capstone.teamProj_10.apiTest.item.ProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor // 꼭 필요한 요소(final) 자동 생성
@RestController // JSON 으로 응답하기 위한 RestController 라는 의미
public class ProductRestController {

    private final ProductRepository productRepository;

    // 등록된 전체 관심 상품 조회
    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // 관심 상품 등록
    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto) {
        Product product = new Product(requestDto);
        productRepository.save(product);
        return product;
    }
}