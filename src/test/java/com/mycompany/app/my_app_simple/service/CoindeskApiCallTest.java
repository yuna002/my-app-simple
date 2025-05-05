package com.mycompany.app.my_app_simple.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CoindeskApiCallTest {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void testCallCoindeskApiAndPrint() {
        // 這裡換成你要打的 Coindesk API 地址
        String url = "https://kengp3.github.io/blog/coindesk.json";
   
        // 呼叫 API
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // 確認 HTTP 狀態碼是 200 OK
        assertEquals(200, response.getStatusCode().value());

        // 顯示回應內容
        System.out.println("Coindesk API 回應內容：");
        System.out.println(response.getBody());

        // 簡單驗證回傳內容是不是有 "bpi" 關鍵字
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("bpi"));
    }
}