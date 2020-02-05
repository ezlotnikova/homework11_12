package com.gmail.zlotnikova.repository;

import java.sql.Connection;
import java.sql.SQLException;

import com.gmail.zlotnikova.repository.model.User;

public interface UserRepository extends GeneralRepository<User> {

    User findUser(Connection connection, String username, String password) throws SQLException;

}