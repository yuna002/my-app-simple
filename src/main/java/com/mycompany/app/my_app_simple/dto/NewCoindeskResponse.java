package com.mycompany.app.my_app_simple.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewCoindeskResponse {
    private String updateTime;
    private List<CurrencyInfo> currencyList;

    // Getters and Setters
    @Getter
    @Setter
    public static class CurrencyInfo {
        private String code;
        private String chineseName;
        private BigDecimal rate;

        // Getters and Setters
    }
}

