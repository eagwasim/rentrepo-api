package com.noubug.rentrepo.infrastructure.persistence.entities;

import lombok.*;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Currencies")
public class CurrencyEntity extends AbstractPersistableEntity<Long> {
    private String name;
    private String code;
    private String baseCurrencyExchangeRate;
}
