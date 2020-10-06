package com.noubug.rentrepo.domain.gateway;


import com.noubug.rentrepo.domain.UserDomain;
import com.noubug.rentrepo.domain.model.OAuthUser;

import java.io.IOException;

public interface ClientDomainGateway {

    void blockClientAccess(UserDomain userDomain) throws IOException;

    void notifyUserOfFiledReport(UserDomain userDomain);

    String getClientToken(String userId);

    OAuthUser tokenUser(String token);
}
