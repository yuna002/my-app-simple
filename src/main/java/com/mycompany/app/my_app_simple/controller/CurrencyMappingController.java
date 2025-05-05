package com.mycompany.app.my_app_simple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.app.my_app_simple.model.CurrencyMapping;
import com.mycompany.app.my_app_simple.service.CurrencyMappingService;


@RestController
@RequestMapping("/currency")
public class CurrencyMappingController {

    @Autowired
    private CurrencyMappingService currencyMappingService;
    
    // 查詢所有幣別
    @GetMapping
    public List<CurrencyMapping> getAll() {
        return currencyMappingService.findAll();
    }

    // 根據 code 查詢單一幣別
    @GetMapping("/{code}")
    public ResponseEntity<CurrencyMapping> getOne(@PathVariable String code) {
        return currencyMappingService.findById(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 新增幣別
    @PostMapping
    public ResponseEntity<CurrencyMapping> create(@RequestBody CurrencyMapping currency) {
        if (currency == null || currency.getCode() == null || currency.getEnglishName() == null || currency.getChineseName() == null) {
            return ResponseEntity.badRequest().build();  // 欄位檢查
        }
        
        CurrencyMapping savedCurrency = currencyMappingService.save(currency);
        return ResponseEntity.ok(savedCurrency);
    }

    // 更新幣別
    @PutMapping("/{code}")
    public ResponseEntity<CurrencyMapping> update(@PathVariable String code, @RequestBody CurrencyMapping updated) {
        if (updated == null || updated.getCode() == null || updated.getEnglishName() == null || updated.getChineseName() == null) {
            return ResponseEntity.badRequest().build();  // 欄位檢查
        }
        
        CurrencyMapping updatedCurrency = currencyMappingService.update(code, updated);
        return updatedCurrency != null ? ResponseEntity.ok(updatedCurrency) : ResponseEntity.notFound().build();
    }

    // 刪除幣別
    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable String code) {
        if (currencyMappingService.findById(code).isEmpty()) {
            return ResponseEntity.notFound().build();  // 若找不到該幣別
        }
        currencyMappingService.delete(code);
        return ResponseEntity.noContent().build();  // 成功刪除
    }
}
