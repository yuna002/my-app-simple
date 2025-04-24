package com.mycompany.app.my_app_simple.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/new")
    public NewCoindeskResponse getTransformedCoindeskData() {
        return coindeskService.fetchAndTransform();
    }
}
