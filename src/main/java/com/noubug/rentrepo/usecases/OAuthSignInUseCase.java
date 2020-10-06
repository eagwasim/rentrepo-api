package com.noubug.rentrepo.usecases;

import com.noubug.rentrepo.usecases.model.OAuthSignInResponse;

public interface OAuthSignInUseCase {
    OAuthSignInResponse googleSignIn(String idToken);

    OAuthSignInResponse firebaseSignIn(String idToken);
}
