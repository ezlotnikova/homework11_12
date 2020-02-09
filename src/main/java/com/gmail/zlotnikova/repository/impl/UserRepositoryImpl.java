package com.gmail.zlotnikova.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gmail.zlotnikova.repository.UserRepository;
import com.gmail.zlotnikova.repository.model.Role;
import com.gmail.zlotnikova.repository.model.User;

public class UserRepositoryImpl implements UserRepository {

    private static UserRepository instance;

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public User findUser(Connection connection, String username, String password) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT u.id, username, created_by, u.role, r.description " +
                                "FROM user AS u LEFT JOIN role AS r on u.role = r.name " +
                                "WHERE username=? and password=?")
        ) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return getUser(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<User> findAll(Connection connection) throws SQLException {
        try (
                Statement statement = connection.createStatement()
        ) {
            List<User> users = new ArrayList<>();
            try (
                    ResultSet rs = statement.executeQuery(
                            "SELECT u.id, username, created_by, u.role, r.description FROM user AS u " +
                                    "LEFT JOIN role AS r on u.role = r.name")
            ) {
                while (rs.next()) {
                    User user = getUser(rs);
                    users.add(user);
                }
                return users;
            }
        }
    }

    @Override
    public User add(Connection connection, User user) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO user(username, password, created_by, role) VALUES (?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setLong(3, user.getCreatedBy());
            statement.setString(4, user.getRole().getName());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Adding user failed, no ID obtained.");
                }
            }
            return user;
        }
    }

    private User getUser(ResultSet rs) throws SQLException {

        User user = new User();

        Long id = rs.getLong("id");
        user.setId(id);

        String username = rs.getString("username");
        user.setUsername(username);

        Long createdBy = rs.getLong("created_by");
        user.setCreatedBy(createdBy);

        Role role = new Role();
        String roleName = rs.getString("role");
        role.setName(roleName);
        String description = rs.getString("description");
        role.setDescription(description);
        user.setRole(role);

        return user;
    }

}