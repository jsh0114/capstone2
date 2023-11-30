package com.capstone.teamProj_10.apiTest.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor
@Entity
public class Product {





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


    @Column(nullable = false)
    private int stockQuantity;

    public Product(ProductRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.image = requestDto.getImage();
        this.category2 = requestDto.getCategory2();
        this.category3 = requestDto.getCategory3();
        this.category4 = requestDto.getCategory4();
        this.productId = requestDto.getProductId();
        this.maker = requestDto.getMaker();
        this.myprice = 0;
    }

    public Product(ItemDto itemDto) {
        this.title = itemDto.getTitle();
        this.image = itemDto.getImage();
        this.lprice = itemDto.getLprice();
        this.link = itemDto.getLink();
        this.category2 = itemDto.getCategory2();
        this.category3 = itemDto.getCategory3();
        this.category4 = itemDto.getCategory4();
        this.productId = itemDto.getProductId();
        this.maker = itemDto.getMaker();
        this.myprice = 0;
    }

    public Product(ItemDto itemDto, Long id) {
        this.title = itemDto.getTitle();
        this.image = itemDto.getImage();
        this.lprice = itemDto.getLprice();
        this.link = itemDto.getLink();
    }

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;


    public void updateByItemDto(ItemDto itemDto) {
        this.title = itemDto.getTitle();
        this.image = itemDto.getImage();
        this.lprice = itemDto.getLprice();
        this.link = itemDto.getLink();
        this.category2 = itemDto.getCategory2();
        this.category3 = itemDto.getCategory3();
        this.category4 = itemDto.getCategory4();
        this.productId = itemDto.getProductId();
        this.maker = itemDto.getMaker();
        this.stockQuantity = itemDto.getStockQuantity();
    }
}
