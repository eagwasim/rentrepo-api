package com.noubug.rentrepo.usecases;

import com.noubug.rentrepo.usecases.model.FlatShareRequestCreationRequest;

import java.util.Map;

public interface UserCreatesFlatShareUseCase {
    Long createPost(String username, FlatShareRequestCreationRequest request, Map<String, String> context);
}
