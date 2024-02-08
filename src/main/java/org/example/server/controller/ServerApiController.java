package org.example.server.controller;


import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.beans.Encoder;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    //https://openapi.naver.com/v1/search/local.json
    // ?query=%EC%A3%BC%EC%8B%9D&
    // display=10&
    // start=1&
    // sort=random
    @GetMapping("naver")
    public String naver(){

        String query = "갈비찜";
        String encode = Base64.getEncoder().encodeToString(query.getBytes(StandardCharsets.UTF_8));

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", "갈비집")
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "WHh2qkuWxEJME9MXhmgD")
                .header("X-Naver-Client-Secret", "uDrOoZd1Sq")
                .build();
        ResponseEntity<String> result = restTemplate.exchange(req , String.class);

        return  result.getBody();
    }
    @GetMapping("/hello")
    public String hello(){
        return "hell";
    }
}
