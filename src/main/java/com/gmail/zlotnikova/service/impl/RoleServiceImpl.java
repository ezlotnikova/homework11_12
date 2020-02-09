package com.gmail.zlotnikova.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gmail.zlotnikova.repository.ConnectionRepository;
import com.gmail.zlotnikova.repository.RoleRepository;
import com.gmail.zlotnikova.repository.impl.ConnectionRepositoryImpl;
import com.gmail.zlotnikova.repository.impl.RoleRepositoryImpl;
import com.gmail.zlotnikova.repository.model.Role;
import com.gmail.zlotnikova.service.RoleService;
import com.gmail.zlotnikova.service.model.RoleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static RoleService instance;
    private ConnectionRepository connectionRepository;
    private RoleRepository roleRepository;

    public RoleServiceImpl(ConnectionRepository connectionRepository,
            RoleRepository roleRepository) {
        this.connectionRepository = connectionRepository;
        this.roleRepository = roleRepository;
    }

    public static RoleService getInstance() {
        if (instance == null) {
            instance = new RoleServiceImpl(
                    ConnectionRepositoryImpl.getInstance(),
                    RoleRepositoryImpl.getInstance());
        }
        return instance;
    }

    @Override
    public RoleDTO add(RoleDTO addRoleDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Role databaseRole = convertDTOToDatabaseRole(addRoleDTO);
                Role addedRole = roleRepository.add(connection, databaseRole);
                RoleDTO addedRoleDTO = convertDatabaseRoleToDTO(addedRole);
                connection.commit();
                return addedRoleDTO;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return addRoleDTO;
    }

    @Override
    public List<RoleDTO> findAll() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<Role> roles = roleRepository.findAll(connection);
                List<RoleDTO> roleDTOList = new ArrayList<>();
                for (Role role : roles) {
                    RoleDTO roleDTO = convertDatabaseRoleToDTO(role);
                    roleDTOList.add(roleDTO);
                }
                connection.commit();
                return roleDTOList;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    private Role convertDTOToDatabaseRole(RoleDTO addRoleDTO) {
        Role databaseRole = new Role();
        databaseRole.setName(addRoleDTO.getRoleName());
        databaseRole.setDescription(addRoleDTO.getDescription());
        return databaseRole;
    }

    private RoleDTO convertDatabaseRoleToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRoleName(role.getName());
        roleDTO.setDescription(role.getDescription());
        return roleDTO;
    }

}