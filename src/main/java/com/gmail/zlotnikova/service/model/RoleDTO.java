package com.gmail.zlotnikova.service.model;

public class RoleDTO {

    private Integer id;
    private String roleName;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
                "id = " + id +
                ", role = " + roleName + '\'' +
                ", description = '" + description + '\'' +
                '}';
    }

}