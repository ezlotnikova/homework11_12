package com.gmail.zlotnikova.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gmail.zlotnikova.repository.ConnectionRepository;
import com.gmail.zlotnikova.repository.UserRepository;
import com.gmail.zlotnikova.repository.impl.ConnectionRepositoryImpl;
import com.gmail.zlotnikova.repository.impl.UserRepositoryImpl;
import com.gmail.zlotnikova.repository.model.Role;
import com.gmail.zlotnikova.repository.model.User;
import com.gmail.zlotnikova.service.UserService;
import com.gmail.zlotnikova.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static UserService instance;
    private ConnectionRepository connectionRepository;
    private UserRepository userRepository;

    public UserServiceImpl(ConnectionRepository connectionRepository,
            UserRepository userRepository) {
        this.connectionRepository = connectionRepository;
        this.userRepository = userRepository;
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl(
                    ConnectionRepositoryImpl.getInstance(),
                    UserRepositoryImpl.getInstance());
        }
        return instance;
    }

    @Override
    public UserDTO add(UserDTO addUserDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                User databaseUser = convertDTOToDatabaseUser(addUserDTO);
                User addedUser = userRepository.add(connection, databaseUser);
                UserDTO addedUserDTO = convertDatabaseUserToDTO(addedUser);
                connection.commit();
                return addedUserDTO;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return addUserDTO;
    }

    @Override
    public List<UserDTO> findAll() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<User> users = userRepository.findAll(connection);
                List<UserDTO> userDTOList = new ArrayList<>();
                for (User user : users) {
                    UserDTO userDTO = convertDatabaseUserToDTO(user);
                    userDTOList.add(userDTO);
                }
                connection.commit();
                return userDTOList;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public UserDTO findUser(String username, String password) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                User user = userRepository.findUser(connection, username, password);
                if (user != null) {
                    UserDTO userDTO = convertDatabaseUserToDTO(user);
                    return userDTO;
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private User convertDTOToDatabaseUser(UserDTO userDTO) {
        User databaseUser = new User();
        databaseUser.setUsername(userDTO.getUsername());
        databaseUser.setPassword(userDTO.getPassword());
        databaseUser.setCreatedBy(userDTO.getCreatedBy());
        Role role = new Role();
        role.setName(userDTO.getRoleName());
        databaseUser.setRole(role);
        return databaseUser;
    }

    private UserDTO convertDatabaseUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setCreatedBy(user.getCreatedBy());
        if (user.getRole() != null) {
            userDTO.setRoleName(user.getRole().getName());
            userDTO.setDescription(user.getRole().getDescription());
        }
        return userDTO;
    }

}