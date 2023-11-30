package com.capstone.teamProj_10.apiTest.productRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductRequestService {
    private final ProductRequestRepository productRequestRepository;
    @Transactional
    public void saveItem(ProductRequest productRequest){productRequestRepository.save(productRequest);}

    public List<ProductRequest>findItems(){return productRequestRepository.findAll();}

    @Transactional
    public void deleteProductRequest(Long productId){productRequestRepository.deleteProductRequest(productId);}
}
