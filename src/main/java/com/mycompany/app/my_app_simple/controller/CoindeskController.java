package com.mycompany.app.my_app_simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.app.my_app_simple.dto.NewCoindeskResponse;
import com.mycompany.app.my_app_simple.service.CoindeskService;

@RestController
@RequestMapping("/api/coindesk")
public class CoindeskController {

    @Autowired
    private CoindeskService coindeskService;

    /**
     * 呼叫 Coindesk API 並返回轉換後的資料。
     * @return ResponseEntity 包含轉換後的資料
     */
    @GetMapping("/new")
    public ResponseEntity<NewCoindeskResponse> getTransformedCoindeskData() {
        try {
            // 取得轉換後的資料
            NewCoindeskResponse transformedData = coindeskService.fetchAndTransform();
            // 如果成功，返回 200 OK 並返回資料
            return ResponseEntity.ok(transformedData);
        } catch (Exception e) {
            // 捕捉例外並返回 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
