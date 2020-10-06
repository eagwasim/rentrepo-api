package com.noubug.rentrepo.infrastructure.serviceimpl;

import com.noubug.rentrepo.domain.services.EnvironmentService;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SpringEnvironmentServiceImpl implements EnvironmentService {
    private final Environment environment;

    @Inject
    public SpringEnvironmentServiceImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String getVariable(String key) {
        return environment.getProperty(key);
    }

    @Override
    public int getIntVariable(String key) {
        return Integer.valueOf(getVariable(key));
    }
}
