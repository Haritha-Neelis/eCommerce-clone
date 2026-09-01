package com.ecommerce.model;

public enum UserRole {
    CUSTOMER("ROLE_CUSTOMER"),
    ADMIN("ROLE_ADMIN"),
    SUPPORT("ROLE_SUPPORT");

    private final String roleValue;

    UserRole(String roleValue) {
        this.roleValue = roleValue;
    }

    public String getRoleValue() {
        return roleValue;
    }
}
