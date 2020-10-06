package com.noubug.rentrepo.infrastructure.web.controllers;

import com.noubug.rentrepo.usecases.OAuthSignInUseCase;
import com.noubug.rentrepo.usecases.model.OAuthSignInResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/access")
public class AccessController {
    private final OAuthSignInUseCase oAuthSignInUseCase;

    public AccessController(OAuthSignInUseCase oAuthSignInUseCase) {
        this.oAuthSignInUseCase = oAuthSignInUseCase;
    }

    @RequestMapping(value = "/oauth/google", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> oauthGoogle(@RequestParam("token") String idToken) {
        OAuthSignInResponse response = oAuthSignInUseCase.googleSignIn(idToken);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/oauth/firebase", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> oauthFirebase(@RequestParam("token") String idToken) {
        OAuthSignInResponse response = oAuthSignInUseCase.firebaseSignIn(idToken);
        return ResponseEntity.ok(response);
    }
}
