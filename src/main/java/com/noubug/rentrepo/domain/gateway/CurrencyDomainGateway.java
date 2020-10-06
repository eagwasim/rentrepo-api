package com.noubug.rentrepo.domain.gateway;

import com.noubug.rentrepo.domain.CurrencyDomain;

import java.math.BigDecimal;

public interface CurrencyDomainGateway {
    void create(CurrencyDomain currencyDomain);

    void updateBaseCurrencyExchangeRate(CurrencyDomain currencyDomain);

    BigDecimal toBaseCurrency(String currencyCode, BigDecimal value);
}
