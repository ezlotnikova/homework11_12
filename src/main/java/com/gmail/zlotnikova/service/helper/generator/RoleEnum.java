package com.gmail.zlotnikova.service.helper.generator;

public enum RoleEnum {

    USER("User", "Able to see Users List"),
    ADMIN("Admin", "Able to see Roles List");

    private final String roleName;
    private final String description;

    RoleEnum(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getDescription() {
        return description;
    }

}