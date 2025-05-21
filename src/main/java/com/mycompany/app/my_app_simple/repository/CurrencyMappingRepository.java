package com.mycompany.app.my_app_simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.app.my_app_simple.model.CurrencyMapping;

public interface CurrencyMappingRepository extends JpaRepository<CurrencyMapping, String> {
}
