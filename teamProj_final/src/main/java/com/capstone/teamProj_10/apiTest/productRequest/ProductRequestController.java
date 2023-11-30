package com.capstone.teamProj_10.apiTest.productRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductRequestController {
    private final ProductRequestService productRequestService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cart")
    public String cart(Model model) {
        List<ProductRequest> productRequestList = productRequestService.findItems();
        model.addAttribute("productRequest",productRequestList);
        return "cart";
    }
    @DeleteMapping("/api/productRequests/{id}")
    public ResponseEntity<Void>deleteProductRequest(@PathVariable Long id){
        productRequestService.deleteProductRequest(id);
        return ResponseEntity.noContent().build();
    }
}
