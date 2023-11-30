package com.capstone.teamProj_10.apiTest.productRequest;

import com.capstone.teamProj_10.apiTest.item.ItemService;
import com.capstone.teamProj_10.apiTest.item.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductRequestRestController {
    private final ProductRequestService productRequestService;
    private final ItemService itemService;

    @PostMapping("/api/products/request")
    public ResponseEntity<Void> addToProductRequest(@RequestBody ProductRequestDto requestDto) {
        Product product = itemService.findOne(requestDto.getProductId());

        if (product == null) {
            throw new IllegalArgumentException("Product with id " + requestDto.getProductId() + " not found");
        }

        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductId(product.getProductId());
        productRequest.setTitle(product.getTitle());
        productRequest.setImage(product.getImage());
        productRequest.setLink(product.getLink());
        productRequest.setCategory2(product.getCategory2());
        productRequest.setCategory3(product.getCategory3());
        productRequest.setCategory4(product.getCategory4());
        productRequest.setMaker(product.getMaker());
        productRequest.setLprice(product.getLprice());

        productRequestService.saveItem(productRequest);

        return ResponseEntity.ok().build();
    }

}
