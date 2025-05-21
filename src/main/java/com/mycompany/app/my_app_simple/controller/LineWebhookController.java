package com.mycompany.app.my_app_simple.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/callback")
public class LineWebhookController {

    @PostMapping
    public ResponseEntity<String> callback(@RequestBody String payload) {
        try {
            // 使用 Jackson 解析 JSON
            ObjectMapper objectMapper = new ObjectMapper();
            // 將 JSON 字符串轉換為帶有類型參數的 Map
            @SuppressWarnings("unchecked")
            Map<String, Object> jsonPayload = objectMapper.readValue(payload, Map.class);
            
            // 取得事件列表
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> events = (List<Map<String, Object>>) jsonPayload.get("events");
            
            if (!events.isEmpty()) {
                // 取得用戶 ID
                @SuppressWarnings("unchecked")
                String userId = (String) ((Map<String, Object>) events.get(0).get("source")).get("userId");
                System.out.println("User ID: " + userId);
            } else {
                System.out.println("No events found in payload.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing payload.");
        }

        return ResponseEntity.ok("OK");
    }
}
