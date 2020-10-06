package com.noubug.rentrepo.usecases.impl;

import com.noubug.rentrepo.domain.UserDomain;
import com.noubug.rentrepo.domain.gateway.ClientDomainGateway;
import com.noubug.rentrepo.domain.gateway.UserDomainGateway;
import com.noubug.rentrepo.domain.gateway.WebDomainGateway;
import com.noubug.rentrepo.domain.model.OAuthUser;
import com.noubug.rentrepo.domain.services.EnvironmentService;
import com.noubug.rentrepo.domain.services.OAuthService;
import com.noubug.rentrepo.usecases.OAuthSignInUseCase;
import com.noubug.rentrepo.usecases.model.OAuthSignInResponse;
import com.noubug.rentrepo.usecases.model.UserModel;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Named
public class OAuthSignInUseCaseImpl implements OAuthSignInUseCase {
    private final OAuthService oAuthService;
    private final UserDomainGateway userDomainGateway;
    private final WebDomainGateway webDomainGateway;
    private final EnvironmentService environmentService;
    private final ClientDomainGateway clientDomainGateway;

    public OAuthSignInUseCaseImpl(OAuthService oAuthService, UserDomainGateway userDomainGateway, WebDomainGateway webDomainGateway, EnvironmentService environmentService, ClientDomainGateway clientDomainGateway) {
        this.oAuthService = oAuthService;
        this.userDomainGateway = userDomainGateway;
        this.webDomainGateway = webDomainGateway;
        this.environmentService = environmentService;
        this.clientDomainGateway = clientDomainGateway;
    }

    @Override
    public OAuthSignInResponse googleSignIn(String idToken) {
        OAuthUser oAuthUser = this.oAuthService.fromGoogleIdToken(idToken);
        return from(oAuthUser);
    }

    @Override
    public OAuthSignInResponse firebaseSignIn(String idToken) {
        OAuthUser oAuthUser = this.clientDomainGateway.tokenUser(idToken);
        return from(oAuthUser);
    }

    private OAuthSignInResponse from(OAuthUser oAuthUser) {
        UserDomain userDomain = userDomainGateway.fromOAuthUser(oAuthUser);

        String token = webDomainGateway.generateToken(
                userDomain.getId(),
                userDomain.getEmail(),
                userDomain.getAuthKey(),
                LocalDateTime.now(ZoneOffset.UTC)
                        .plusHours(environmentService.getIntVariable("security.access.token.expiration.hours"))
        );

        String firebaseToken = this.clientDomainGateway.getClientToken(userDomain.getId().toString());

        return OAuthSignInResponse
                .builder()
                .fireBaseToken(firebaseToken)
                .user(
                        UserModel.builder()
                                .addressVerified(userDomain.isAddressVerified())
                                .authKey(userDomain.getAuthKey())
                                .email(userDomain.getEmail())
                                .emailVerified(userDomain.isEmailVerified())
                                .gender(userDomain.getGender())
                                .identityVerified(userDomain.isIdentityVerified())
                                .isFirstLogin(userDomain.isFirstLogin())
                                .name(userDomain.getName())
                                .phone(userDomain.getPhone())
                                .phoneVerified(userDomain.isPhoneVerified())
                                .photo(userDomain.getPhoto())
                                .roles(userDomain.getRoles())
                                .status(userDomain.getStatus())
                                .id(userDomain.getId())
                                .build()
                ).token(token)
                .build();
    }
}
