package com.mycompany.app.my_app_simple.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="currency_mapping")
public class CurrencyMapping {
	@Id
	private String code;
	private String englishName;
	private String chineseName;
	
	@Override
    public String toString() {
        return "CurrencyMapping{" +
                "code='" + code + '\'' +
                ", englishName='" + englishName + '\'' +
                ", chineseName='" + chineseName + '\'' +
                '}';
    }
	
}
