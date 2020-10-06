package com.noubug.rentrepo.infrastructure.gatewayimpl;

import com.noubug.rentrepo.domain.CurrencyDomain;
import com.noubug.rentrepo.domain.gateway.CurrencyDomainGateway;
import com.noubug.rentrepo.infrastructure.persistence.entities.CurrencyEntity;
import com.noubug.rentrepo.infrastructure.persistence.enumeration.EntityStatusConstant;
import com.noubug.rentrepo.infrastructure.persistence.repositories.CurrencyEntityRepository;

import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Named
public class CurrencyDomainGatewayImpl implements CurrencyDomainGateway {
    private final CurrencyEntityRepository currencyEntityRepository;

    public CurrencyDomainGatewayImpl(CurrencyEntityRepository currencyEntityRepository) {
        this.currencyEntityRepository = currencyEntityRepository;
    }

    @Override
    public void create(CurrencyDomain currencyDomain) {
        CurrencyEntity currencyEntity = CurrencyEntity.builder()
                .code(currencyDomain.getCode())
                .name(currencyDomain.getName())
                .baseCurrencyExchangeRate("0.0")
                .build();

        currencyEntity.setDateCreated(LocalDateTime.now(ZoneOffset.UTC));
        currencyEntity.setStatus(EntityStatusConstant.ACTIVE);
        currencyEntity.setDateModified(LocalDateTime.now(ZoneOffset.UTC));

        currencyEntityRepository.save(currencyEntity);
    }

    @Override
    public void updateBaseCurrencyExchangeRate(CurrencyDomain currencyDomain) {
        Optional<CurrencyEntity> optionalCurrencyEntity = currencyEntityRepository.findFirstByCode(currencyDomain.getCode());
        if (optionalCurrencyEntity.isEmpty()) {
            return;
        }

        CurrencyEntity currencyEntity = optionalCurrencyEntity.get();
        currencyEntity.setBaseCurrencyExchangeRate(currencyDomain.getBaseCurrencyExchangeRate().toString());
        currencyEntity.setDateModified(LocalDateTime.now(ZoneOffset.UTC));

        currencyEntityRepository.save(currencyEntity);
    }

    @Override
    public BigDecimal toBaseCurrency(String currencyCode, BigDecimal value) {
        Optional<CurrencyEntity> optionalCurrencyEntity = currencyEntityRepository.findFirstByCode(currencyCode);

        if (optionalCurrencyEntity.isEmpty()) {
            return BigDecimal.ZERO;
        }

        CurrencyEntity currencyEntity = optionalCurrencyEntity.get();

        return value.divide(new BigDecimal(currencyEntity.getBaseCurrencyExchangeRate()), RoundingMode.UNNECESSARY);
    }
}
