package com.capstone.teamProj_10.apiTest.item;


import com.capstone.teamProj_10.apiTest.item.ItemDto;
import com.capstone.teamProj_10.apiTest.item.Product;
import com.capstone.teamProj_10.apiTest.item.ItemService;
import com.capstone.teamProj_10.apiTest.utils.NaverShopSearch;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchRequestController {
    private final NaverShopSearch naverShopSearch;

    private final ItemService itemService;

    private final ItemRepository itemRepository;

    @GetMapping("/api/search")
    public List<Product> getItems(@RequestParam String query) {
        String result = naverShopSearch.search(query); //search 를 호출해 결과를 String 으로 받아옴
        List<ItemDto> itemDtoList = naverShopSearch.fromJSONtoItems(result); //결과 저장
        List<Product> productList = new ArrayList<>();

        for (ItemDto itemDto : itemDtoList) {
            Product product = new Product(itemDto);
            itemService.saveItem(product);
            productList.add(product);
        }

        return productList;
    }
}