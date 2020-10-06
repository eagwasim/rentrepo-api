package com.noubug.rentrepo.domain.gateway;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface WebDomainGateway {
    String generateToken(Serializable id, String username, String authKey, LocalDateTime expiration);
}
