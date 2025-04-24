package com.mycompany.app.my_app_simple.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mycompany.app.my_app_simple.dto.NewCoindeskResponse;
import com.mycompany.app.my_app_simple.model.CurrencyMapping;
import com.mycompany.app.my_app_simple.repository.CurrencyMappingRepository;

@SpringBootTest
public class CoindeskServiceTest {

    @InjectMocks
    private CoindeskService coindeskService;

    @Mock
    private CurrencyMappingRepository currencyMappingRepository;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // 初始化 mock
    }

    @Test
    void testFetchAndTransform() {
        // 模擬 Coindesk API 回傳的資料
        Map<String, Object> mockResponse = Map.of(
            "time", Map.of("updatedISO", "2024-09-02T07:07:20+00:00"),
            "bpi", Map.of(
                "USD", Map.of("rate_float", 57756.2984)
            )
        );

        // 模擬 RestTemplate 呼叫 API 回傳值
        when(restTemplate.getForEntity(anyString(), eq(Map.class))).thenReturn(ResponseEntity.ok(mockResponse));

        // ✅ 模擬幣別對應資料（正確）
        CurrencyMapping usdMapping = new CurrencyMapping();
        usdMapping.setCode("USD");
        usdMapping.setChineseName("美元");
        when(currencyMappingRepository.findById("USD")).thenReturn(java.util.Optional.of(usdMapping));

        // 執行測試
        NewCoindeskResponse result = coindeskService.fetchAndTransform();

        // 驗證結果
        assertNotNull(result);
        assertEquals("2024/09/02 07:07:20", result.getUpdateTime());

        assertEquals("美元", result.getCurrencyList().get(0).getChineseName());

        BigDecimal expectedRate = new BigDecimal("57756.298").setScale(3, RoundingMode.HALF_UP);
        BigDecimal actualRate = result.getCurrencyList().get(0).getRate().setScale(3, RoundingMode.HALF_UP);
        assertEquals(expectedRate, actualRate);
    }

}
