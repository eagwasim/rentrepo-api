package com.noubug.rentrepo.infrastructure.gatewayimpl;

import com.noubug.rentrepo.domain.gateway.WebDomainGateway;
import com.noubug.rentrepo.infrastructure.web.security.JwtTokenProvider;

import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;

@Named
public class WebDomainGatewayImpl implements WebDomainGateway {
    private final JwtTokenProvider jwtTokenProvider;

    public WebDomainGatewayImpl(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String generateToken(Serializable id, String username, String authKey, LocalDateTime expiration) {
        return jwtTokenProvider.createToken(id, username, authKey, expiration);
    }
}
