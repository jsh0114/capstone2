package com.capstone.teamProj_10.apiTest.productRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ProductRequest {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;
    @Column(nullable = false)
    private String category2;

    private String category3;

    private String category4;
    @Column(nullable = false)
    private String maker;
    @Id
    @Column(name = "item_id")
    private Long productId;
    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

}
