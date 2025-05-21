package com.mycompany.app.my_app_simple.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.app.my_app_simple.model.CurrencyMapping;
import com.mycompany.app.my_app_simple.repository.CurrencyMappingRepository;

@Service
public class CurrencyMappingService {
	@Autowired
	private CurrencyMappingRepository currencyMappingRepository;
	
	public List<CurrencyMapping> findAll() {
		return currencyMappingRepository.findAll();
	}
	
	public CurrencyMapping save(CurrencyMapping currency) {
		return currencyMappingRepository.save(currency);
	}
	
	public CurrencyMapping update(String code, CurrencyMapping updated) {
	    return currencyMappingRepository.findById(code)
	        .map(existing -> {
	            existing.setChineseName(updated.getChineseName());
	            return currencyMappingRepository.save(existing); // <- 要記得存回去！
	        })
	        .orElseThrow(() -> new RuntimeException("Currency code not found: " + code));
	}
	
	public void delete(String code) {
		currencyMappingRepository.deleteById(code);
	} 
	
	public Optional<CurrencyMapping> findById(String code) {
		return currencyMappingRepository.findById(code);
	}
	
}
