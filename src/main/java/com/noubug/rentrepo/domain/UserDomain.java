package com.noubug.rentrepo.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserDomain {
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

    private LocalDateTime dateCreated;
    private LocalDateTime lastUpdated;

}
