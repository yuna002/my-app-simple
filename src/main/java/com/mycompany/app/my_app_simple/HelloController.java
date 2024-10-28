package com.mycompany.app.my_app_simple;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(value = "/")
	public String spring() {
		return "Spring Boot!!";
	}

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
    
    @GetMapping("/test")
    public String test() {
        return "test!";
    }
    
    /*@PostMapping("/callback")
    public String callback(@RequestBody String payload) {
        // 處理接收到的 LINE Webhook 訊息
        System.out.println("Received payload: " + payload);
        return "OK";
    }*/
    
}
