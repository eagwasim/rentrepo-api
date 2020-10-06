package com.noubug.rentrepo.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OAuthUser {
    private String name;
    private String email;
    private String photoUrl;

    private boolean emailVerified;
}
