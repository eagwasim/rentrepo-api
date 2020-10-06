package com.noubug.rentrepo.infrastructure.gatewayimpl;

import com.noubug.rentrepo.domain.UserDomain;
import com.noubug.rentrepo.domain.gateway.UserDomainGateway;
import com.noubug.rentrepo.domain.model.OAuthUser;
import com.noubug.rentrepo.infrastructure.persistence.entities.UserEntity;
import com.noubug.rentrepo.infrastructure.persistence.enumeration.GenderStatusConstant;
import com.noubug.rentrepo.infrastructure.persistence.enumeration.UserRoleConstant;
import com.noubug.rentrepo.infrastructure.persistence.repositories.UserEntityRepository;
import org.apache.commons.lang3.RandomStringUtils;

import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Named
public class UserDomainGatewayImpl implements UserDomainGateway {
    private final UserEntityRepository userEntityRepository;

    public UserDomainGatewayImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDomain fromOAuthUser(OAuthUser oAuthUser) {
        Optional<UserEntity> optionalUserEntity = userEntityRepository.findFirstByEmail(oAuthUser.getEmail().toLowerCase());

        if (optionalUserEntity.isPresent()) {
            return fromEntity(optionalUserEntity.get());
        }

        UserEntity userEntity = UserEntity.builder()
                .addressVerified(false)
                .authKey(RandomStringUtils.randomAlphanumeric(6))
                .addressVerified(false)
                .emailVerified(oAuthUser.isEmailVerified())
                .gender(GenderStatusConstant.UNKNOWN)
                .identityVerified(false)
                .name(oAuthUser.getName())
                .phoneVerified(false)
                .photo(oAuthUser.getPhotoUrl())
                .role(UserRoleConstant.USER)
                .email(oAuthUser.getEmail())
                .build();

        userEntity.setDateCreated(LocalDateTime.now(ZoneOffset.UTC));
        userEntity.setDateModified(LocalDateTime.now(ZoneOffset.UTC));

        userEntityRepository.save(userEntity);

        UserDomain userDomain = fromEntity(userEntity);
        userDomain.setFirstLogin(true);

        return userDomain;
    }

    private UserDomain fromEntity(UserEntity userEntity) {
        return UserDomain.builder()
                .addressVerified(userEntity.isAddressVerified())
                .dateCreated(userEntity.getDateCreated())
                .email(userEntity.getEmail())
                .emailVerified(userEntity.isEmailVerified())
                .gender(userEntity.getGender().name())
                .id(userEntity.getId())
                .identityVerified(userEntity.isIdentityVerified())
                .lastUpdated(userEntity.getDateModified())
                .name(userEntity.getName())
                .phone(userEntity.getPhone())
                .phoneVerified(userEntity.isPhoneVerified())
                .photo(userEntity.getPhoto())
                .roles(userEntity.getRole().getRoles())
                .status(userEntity.getStatus().name())
                .authKey(userEntity.getAuthKey())
                .build();
    }
}
