package com.noubug.rentrepo.infrastructure.serviceimpl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.noubug.rentrepo.domain.model.OAuthUser;
import com.noubug.rentrepo.domain.services.OAuthService;

import javax.inject.Named;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;


@Named
public class OAuthServiceImpl implements OAuthService {
    private static final JacksonFactory JACKSON_FACTORY = new JacksonFactory();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final String GOOGLE_WEB_CLIENT_ID = "15608698535-k0385jfgsibc75ghr59c7k4ok0096tgu.apps.googleusercontent.com";

    private static final GoogleIdTokenVerifier GOOGLE_ID_TOKEN_VERIFIER = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JACKSON_FACTORY)
            // Specify the CLIENT_ID of the app that accesses the backend:
            .setAudience(Collections.singletonList(GOOGLE_WEB_CLIENT_ID))
            // Or, if multiple clients access the backend:
            //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
            .build();

    @Override
    public OAuthUser fromGoogleIdToken(String token) {
        try {
            GoogleIdToken idToken = GOOGLE_ID_TOKEN_VERIFIER.verify(token);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                // Print user identifier
                String userId = payload.getSubject();
                System.out.println("User ID: " + userId);

                // Get profile information from payload
                String email = payload.getEmail();
                boolean emailVerified = payload.getEmailVerified() == null ? false : payload.getEmailVerified();
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
              /*  String locale = (String) payload.get("locale");
                String familyName = (String) payload.get("family_name");
                String givenName = (String) payload.get("given_name");*/

                return OAuthUser.builder()
                        .email(email)
                        .emailVerified(emailVerified)
                        .name(name)
                        .photoUrl(pictureUrl)
                        .build();
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
