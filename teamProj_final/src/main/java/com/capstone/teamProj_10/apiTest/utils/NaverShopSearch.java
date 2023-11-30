package com.capstone.teamProj_10.apiTest.utils;


import com.capstone.teamProj_10.apiTest.item.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class NaverShopSearch {

    public String search(String query) { //query 를 파라미터로 네이버 쇼핑 API 를 사용하여 검색
        RestTemplate rest = new RestTemplate();//HTTP 클라이언트 ,Json 데이터 송수신
        HttpHeaders headers = new HttpHeaders(); //본문과 헤더 설정
        headers.add("X-Naver-Client-Id", "S3xowYlDYXpN52Qyur4w");
        headers.add("X-Naver-Client-Secret", "4ws8LoYgtw"); //네이버 api 인증정보
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange //GET 요청 수행 
                ("https://openapi.naver.com/v1/search/shop.json?query="
                        + query+ "&display=100", HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = (HttpStatus) responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return response;
    }

    public List<ItemDto> fromJSONtoItems(String result) {
        JSONObject rjson = new JSONObject(result);
        JSONArray items = rjson.getJSONArray("items");

        List<ItemDto> itemDtoList = new ArrayList<>(); 

        for (int i = 0; i < items.length(); i++) {
            JSONObject itemJson = items.getJSONObject(i); //현재 JSON 입력
            ItemDto itemDto = new ItemDto(itemJson);// JSONObject 를 사용하여 itemDto 생성
            itemDtoList.add(itemDto);               //생성된 itemDto 객체를 List 에 추가
        }

        return itemDtoList;                         // List 반환
    }
}
