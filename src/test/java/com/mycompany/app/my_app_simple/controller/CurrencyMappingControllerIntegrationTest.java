package com.mycompany.app.my_app_simple.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.mycompany.app.my_app_simple.model.CurrencyMapping;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")  // 指定使用 application-test.properties
public class CurrencyMappingControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseUrl = "/currency";
    private static final String TEST_CODE = "USD";

    private CurrencyMapping getTestCurrency() {
        CurrencyMapping currency = new CurrencyMapping();
        currency.setCode(TEST_CODE);
        currency.setEnglishName("US Dollar");
        currency.setChineseName("美元");
        return currency;
    }

    @Test
    @Order(1)
    void testCreateCurrency() {
        CurrencyMapping currency = getTestCurrency();
        ResponseEntity<CurrencyMapping> response = restTemplate.postForEntity(baseUrl, currency, CurrencyMapping.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("建立結果: " + response.getBody());
    }

    @Test
    @Order(2)
    void testGetAllCurrencies() {
        ResponseEntity<CurrencyMapping[]> response = restTemplate.getForEntity(baseUrl, CurrencyMapping[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("查詢全部幣別: " + Arrays.toString(response.getBody()));
    }

    @Test
    @Order(3)
    void testGetCurrencyByCode() {
        ResponseEntity<CurrencyMapping> response = restTemplate.getForEntity(baseUrl + "/" + TEST_CODE, CurrencyMapping.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        System.out.println("查詢單筆幣別: " + response.getBody());
    }

    @Test
    @Order(4)
    void testUpdateCurrency() {
        CurrencyMapping updated = getTestCurrency();
        updated.setChineseName("美金");
        HttpEntity<CurrencyMapping> requestEntity = new HttpEntity<>(updated);
        restTemplate.exchange(baseUrl + "/" + TEST_CODE, HttpMethod.PUT, requestEntity, CurrencyMapping.class);

        CurrencyMapping result = restTemplate.getForObject(baseUrl + "/" + TEST_CODE, CurrencyMapping.class);
        assertEquals("美金", result.getChineseName());
        System.out.println("更新後結果: " + result);
    }

    @Test
    @Order(5)
    void testDeleteCurrency() {
        restTemplate.delete(baseUrl + "/" + TEST_CODE);
        ResponseEntity<CurrencyMapping> response = restTemplate.getForEntity(baseUrl + "/" + TEST_CODE, CurrencyMapping.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        System.out.println("刪除後查詢結果: NOT FOUND");
    }
}
