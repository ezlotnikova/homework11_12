package com.gmail.zlotnikova.repository.enums;

public enum CreateActionEnum {

    CREATE_ROLE_TABLE("CREATE TABLE IF NOT EXISTS role " +
            "(" +
            "id TINYINT PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(40) NOT NULL, " +
            "description VARCHAR (100) NOT NULL" +
            ");"),

    CREATE_USER_TABLE("CREATE TABLE IF NOT EXISTS user " +
            "(" +
            "id INT(11) PRIMARY KEY AUTO_INCREMENT, " +
            "username VARCHAR(40) NOT NULL, " +
            "password VARCHAR(40) NOT NULL, " +
            "created_by BIGINT UNSIGNED NOT NULL, " +
            "role VARCHAR(40) " +
            ");");

    private final String query;

    CreateActionEnum(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}