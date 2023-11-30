package com.capstone.teamProj_10.apiTest.item;



import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
@Slf4j
@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    @Transactional
    public int deleteAll() {
        int deletedCount = em.createQuery("DELETE FROM Product").executeUpdate();
        em.clear();
        return deletedCount;
    }

    public void saveAll(List<Product> items){
        for (Product item : items){
            em.persist(item);
        }
    }

    public void save(Product item){
        if(item.getProductId() == null){
            em.persist(item);
        }else{
            em.merge(item);
        }
    }
    public Product findOne(Long id){
        return em.find(Product.class, id);
    }
    public List<Product> findAll(){
        return em.createQuery("select i from Product i",Product.class)
                .getResultList();
    }

}
