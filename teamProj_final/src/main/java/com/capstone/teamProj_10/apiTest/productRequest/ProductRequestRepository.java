package com.capstone.teamProj_10.apiTest.productRequest;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRequestRepository {
    private final EntityManager em;
    public void save(ProductRequest productRequest){
        if(productRequest.getProductId() == null){
            em.persist(productRequest);
        }else{
            em.merge(productRequest);
        }
    }
    public List<ProductRequest>findAll(){
        return em.createQuery("select p from ProductRequest p", ProductRequest.class).getResultList();
    }
    public void deleteProductRequest(Long productId){
        ProductRequest productRequest =em.find(ProductRequest.class,productId);
        if(productRequest == null){
            throw new IllegalArgumentException("ProductRequest with productId" + productId + "not found");
        }
        em.remove(productRequest);
    }
}
