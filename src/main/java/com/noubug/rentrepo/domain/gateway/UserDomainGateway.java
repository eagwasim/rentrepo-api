package com.noubug.rentrepo.domain.gateway;

import com.noubug.rentrepo.domain.UserDomain;
import com.noubug.rentrepo.domain.model.OAuthUser;

public interface UserDomainGateway {
    UserDomain fromOAuthUser(OAuthUser oAuthUser);

    UserDomain fromUsername(String username);
}
