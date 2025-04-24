package com.mycompany.app.my_app_simple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping
	public List<CurrencyMapping> getAll() {
		return currencyMappingService.findAll();
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<CurrencyMapping> getOne(@PathVariable String code) {
		return currencyMappingService.findById(code)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public CurrencyMapping create(@RequestBody CurrencyMapping currency) {
		System.out.println("Saving currency: " + currency);
		return currencyMappingService.save(currency);
    }

    @PutMapping("/{code}")
    public CurrencyMapping update(@PathVariable String code, @RequestBody CurrencyMapping updated) {
        return currencyMappingService.update(code, updated);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        currencyMappingService.delete(code);
    }
	
	
	
	
}
