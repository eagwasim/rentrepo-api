package com.noubug.rentrepo.domain.services;

import com.noubug.rentrepo.domain.model.OAuthUser;

public interface OAuthService {
    OAuthUser fromGoogleIdToken(String token);
}
