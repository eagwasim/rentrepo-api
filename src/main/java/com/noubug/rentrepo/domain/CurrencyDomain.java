package com.noubug.rentrepo.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CurrencyDomain {
    private Long id;
    private String name;
    private String code;
    private BigDecimal baseCurrencyExchangeRate;
}
