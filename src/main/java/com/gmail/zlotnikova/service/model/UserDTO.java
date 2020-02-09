package com.gmail.zlotnikova.service.model;

public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private Long createdBy;
    private String roleName;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
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
                ", username = '" + username + '\'' +
                ", created = " + createdBy +
                ", role = '" + roleName + '\'' +
                ", description = '" + description + '\'' +
                '}';
    }

}