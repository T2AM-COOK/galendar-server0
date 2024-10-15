package com.k.garlander.entity.enums;

public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    final String role;

    Role(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}
