package com.noubug.rentrepo.usecases.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OAuthSignInResponse<T> {
    private T user;
    private String token;
    private String fireBaseToken;
}
