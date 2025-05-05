package com.mycompany.app.my_app_simple.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.mycompany.app.my_app_simple.dto.NewCoindeskResponse;
import com.mycompany.app.my_app_simple.service.CoindeskService;

@WebMvcTest(CoindeskController.class)
public class CoindeskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoindeskService coindeskService;

    @Test
    public void testGetTransformedCoindeskData() throws Exception {
        // 創建 CurrencyInfo
        NewCoindeskResponse.CurrencyInfo mockCurrencyInfo = new NewCoindeskResponse.CurrencyInfo();
        mockCurrencyInfo.setCode("USD");
        mockCurrencyInfo.setChineseName("美元");
        mockCurrencyInfo.setRate(new BigDecimal("57756.298"));

        // 創建 currencyList 並添加 mockCurrencyInfo
        List<NewCoindeskResponse.CurrencyInfo> currencyList = new ArrayList<>();
        currencyList.add(mockCurrencyInfo);

        // 創建 mockResponse
        NewCoindeskResponse mockResponse = new NewCoindeskResponse();
        mockResponse.setUpdateTime("1990/01/01 00:00:00");
        mockResponse.setCurrencyList(currencyList);

        // 當 coindeskService.fetchAndTransform() 被呼叫時，回傳 mockResponse
        when(coindeskService.fetchAndTransform()).thenReturn(mockResponse);

        // 執行測試
        mockMvc.perform(get("/api/coindesk/new"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.updateTime").value("1990/01/01 00:00:00"))
                .andExpect(jsonPath("$.currencyList[0].code").value("USD"))
                .andExpect(jsonPath("$.currencyList[0].chineseName").value("美元"))
                .andExpect(jsonPath("$.currencyList[0].rate").value("57756.298"));
    }
}
