package com.noubug.rentrepo.infrastructure.persistence.entities;

import com.noubug.rentrepo.infrastructure.persistence.enumeration.EntityStatusConstant;
import com.noubug.rentrepo.infrastructure.persistence.enumeration.GenderStatusConstant;
import com.noubug.rentrepo.infrastructure.persistence.enumeration.UserRoleConstant;
import lombok.*;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users")
public class UserEntity extends AbstractPersistableEntity<Long> implements UserDetails {
    private String name;
    private String phone;
    private String email;
    private String authKey;
    private String photo;
    private String password;

    private boolean emailVerified;
    private boolean phoneVerified;
    private boolean identityVerified;
    private boolean addressVerified;

    private UserRoleConstant role;
    private GenderStatusConstant gender;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(role.getRoles()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return getStatus() != EntityStatusConstant.EXPIRED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return getStatus() != EntityStatusConstant.BLOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getStatus() != EntityStatusConstant.INACTIVE;
    }

    @Override
    public boolean isEnabled() {
        return getStatus() != EntityStatusConstant.ACTIVE;
    }
}
