package com.mycompany.app.my_app_simple.service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mycompany.app.my_app_simple.dto.NewCoindeskResponse;
import com.mycompany.app.my_app_simple.model.CurrencyMapping;
import com.mycompany.app.my_app_simple.repository.CurrencyMappingRepository;

@Service
public class CoindeskService {

    @Autowired
    private CurrencyMappingRepository currencyMappingRepository;

    private final RestTemplate restTemplate;

    private final String coindeskUrl = "https://kengp3.github.io/blog/coindesk.json";

    public CoindeskService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public NewCoindeskResponse fetchAndTransform() {
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
            coindeskUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Map<String, Object>>() {}
        );

        Map<String, Object> raw = response.getBody();

        Map<String, String> timeMap = (Map<String, String>) raw.get("time");
        String updatedISO = timeMap.get("updatedISO");
        String formattedTime = formatIsoToCustom(updatedISO);

        Map<String, Map<String, Object>> bpi = (Map<String, Map<String, Object>>) raw.get("bpi");

        List<NewCoindeskResponse.CurrencyInfo> currencyList = new ArrayList<>();

        for (String code : bpi.keySet()) {
            Map<String, Object> info = bpi.get(code);
            CurrencyMapping mapping = currencyMappingRepository.findById(code).orElse(null);

            NewCoindeskResponse.CurrencyInfo item = new NewCoindeskResponse.CurrencyInfo();
            item.setCode(code);
            item.setChineseName(mapping != null ? mapping.getChineseName() : "未知");
            item.setRate(new BigDecimal(info.get("rate_float").toString()));

            currencyList.add(item);
        }

        NewCoindeskResponse result = new NewCoindeskResponse();
        result.setUpdateTime(formattedTime);
        result.setCurrencyList(currencyList);
        return result;
    }

    private String formatIsoToCustom(String isoTime) {
        ZonedDateTime zdt = ZonedDateTime.parse(isoTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return zdt.format(formatter);
    }
}
