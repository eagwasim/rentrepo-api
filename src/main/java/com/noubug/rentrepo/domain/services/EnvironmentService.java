package com.noubug.rentrepo.domain.services;

public interface EnvironmentService {
    String getVariable(String key);
    int getIntVariable(String key);

}
