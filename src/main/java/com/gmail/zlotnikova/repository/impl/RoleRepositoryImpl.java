package com.gmail.zlotnikova.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gmail.zlotnikova.repository.RoleRepository;
import com.gmail.zlotnikova.repository.model.Role;

public class RoleRepositoryImpl implements RoleRepository {

    private static RoleRepository instance;

    private RoleRepositoryImpl() {
    }

    public static RoleRepository getInstance() {
        if (instance == null) {
            instance = new RoleRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Role add(Connection connection, Role role) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO role(name, description) VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, role.getName());
            statement.setString(2, role.getDescription());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    role.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Adding user failed, no ID obtained.");
                }
            }
            return role;
        }
    }

    @Override
    public List<Role> findAll(Connection connection) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id, name, description FROM role"
                )
        ) {
            List<Role> roles = new ArrayList<>();
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Role role = getRole(rs);
                    roles.add(role);
                }
                return roles;
            }
        }
    }

    private Role getRole(ResultSet rs) throws SQLException {

        Role role = new Role();

        Integer id = rs.getInt("id");
        role.setId(id);

        String roleName = rs.getString("name");
        role.setName(roleName);

        String description = rs.getString("description");
        role.setDescription(description);

        return role;
    }

}