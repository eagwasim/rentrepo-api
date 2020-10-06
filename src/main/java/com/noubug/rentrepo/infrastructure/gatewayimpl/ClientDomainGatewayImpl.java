package com.noubug.rentrepo.infrastructure.gatewayimpl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.noubug.rentrepo.domain.UserDomain;
import com.noubug.rentrepo.domain.gateway.ClientDomainGateway;
import com.noubug.rentrepo.domain.model.OAuthUser;
import com.noubug.rentrepo.infrastructure.exceptions.FirebaseAuthenticationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.ResourceUtils;

import javax.inject.Named;
import java.io.FileNotFoundException;
import java.io.IOException;

@Named
@Log4j2
public class ClientDomainGatewayImpl implements ClientDomainGateway {
    private final FirebaseApp firebaseApp;

    public ClientDomainGatewayImpl() throws IOException {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials
                        .fromStream(ResourceUtils
                                .getURL(ResourceUtils.CLASSPATH_URL_PREFIX + "credentials/rentrepo-firebase-adminsdk-854ts-92480ea542.json")
                                .openStream()))
                .setDatabaseUrl("https://cry-out.firebaseio.com")
                .build();

        firebaseApp = FirebaseApp.initializeApp(options);
    }

    @Override
    public void blockClientAccess(UserDomain userDomain) throws IOException {

    }

    @Override
    public void notifyUserOfFiledReport(UserDomain userDomain) {

    }

    @Override
    public String getClientToken(String userId) {
        try {
            return FirebaseAuth.getInstance(firebaseApp).createCustomToken(userId);
        } catch (FirebaseAuthException e) {
            log.error("Firebase Error", e);
            throw new FirebaseAuthenticationException();
        }
    }

    @Override
    public OAuthUser tokenUser(String token) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
        try {
            FirebaseToken firebaseToken = firebaseAuth.verifyIdToken(token);
            return OAuthUser.builder()
                    .email(firebaseToken.getClaims().get("email").toString())
                    .photoUrl(firebaseToken.getClaims().get("picture").toString())
                    .name(firebaseToken.getClaims().get("name").toString())
                    .emailVerified(firebaseToken.getClaims().get("email_verified") != null && firebaseToken.getClaims().get("email_verified").equals(true))
                    .build();
        } catch (FirebaseAuthException e) {
            log.error("Firebase Error", e);
            throw new FirebaseAuthenticationException();
        }
    }
}
