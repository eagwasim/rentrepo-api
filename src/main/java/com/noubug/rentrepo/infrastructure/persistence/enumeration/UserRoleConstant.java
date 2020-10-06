package com.noubug.rentrepo.infrastructure.persistence.enumeration;

public enum UserRoleConstant {
    USER("USER"),
    SUPPORT("SUPPORT", "USER"),
    ADMIN("ADMIN", "SUPPORT", "USER");

    // These are role accesses
    private String[] roles;

    UserRoleConstant(String... roles) {
        this.roles = roles;
    }

    public String[] getRoles() {
        return roles;
    }
}
