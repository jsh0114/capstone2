package com.capstone.teamProj_10.apiTest.item;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
public class ItemDto {

    private Long id;
    private String title;
    private String name;
    private String link;
    private String image;
    private String category2;
    private String category3;
    private String category4;
    private String maker;
    private Long productId;
    private int lprice;
    private int price;
    private int stockQuantity;


    public ItemDto() {
    }

    public ItemDto(JSONObject itemJson) {
        this.title = itemJson.getString("title");
        this.image = itemJson.getString("image");
        this.lprice = itemJson.getInt("lprice");
        this.link = itemJson.getString("link");
        this.category2 = itemJson.getString("category2");
        this.category3 = itemJson.getString("category3");
        this.category4 = itemJson.getString("category4");
        this.maker =  itemJson.getString("maker");
        this.productId = itemJson.getLong("productId");
    }

    public ItemDto(Product product) {
        this.title = product.getTitle();
        this.image = product.getImage();
        this.lprice = product.getLprice();
        this.link = product.getLink();

    }
}