package com.noubug.rentrepo.usecases.impl;

import com.noubug.rentrepo.domain.UserDomain;
import com.noubug.rentrepo.domain.gateway.PlaceToShareDomainGateway;
import com.noubug.rentrepo.domain.gateway.UserDomainGateway;
import com.noubug.rentrepo.usecases.UserCreatesFlatShareUseCase;
import com.noubug.rentrepo.usecases.model.FlatShareRequestCreationRequest;

import javax.inject.Named;
import java.util.Map;

@Named
public class UserCreatesFlatShareUseCaseImpl implements UserCreatesFlatShareUseCase {
    private final UserDomainGateway userDomainGateway;
    private final PlaceToShareDomainGateway placeToShareDomainGateway;

    public UserCreatesFlatShareUseCaseImpl(UserDomainGateway userDomainGateway, PlaceToShareDomainGateway placeToShareDomainGateway) {
        this.userDomainGateway = userDomainGateway;
        this.placeToShareDomainGateway = placeToShareDomainGateway;
    }

    @Override
    public Long createPost(String username, FlatShareRequestCreationRequest request, Map<String, String> context) {
        UserDomain userDomain = userDomainGateway.fromUsername(username);

        return null;
    }
}
