package com.noubug.rentrepo.usecases.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String photo;
    private String gender;
    private String status;
    private String authKey;

    private String[] roles;

    private boolean emailVerified;
    private boolean phoneVerified;
    private boolean identityVerified;
    private boolean addressVerified;
    private boolean isFirstLogin;
}
