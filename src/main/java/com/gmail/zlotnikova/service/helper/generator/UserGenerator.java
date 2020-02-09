package com.gmail.zlotnikova.service.helper.generator;

import java.util.ArrayList;
import java.util.List;

import com.gmail.zlotnikova.service.RoleService;
import com.gmail.zlotnikova.service.impl.RoleServiceImpl;
import com.gmail.zlotnikova.service.model.RoleDTO;
import com.gmail.zlotnikova.service.model.UserDTO;

public class UserGenerator {

    private static UserGenerator instance;
    private RoleService roleService;

    private UserGenerator(RoleService roleService) {
        this.roleService = roleService;
    }

    public static UserGenerator getInstance() {
        if (instance == null) {
            instance = new UserGenerator(RoleServiceImpl.getInstance());
        }
        return instance;
    }

    public List<UserDTO> generateUsers() {
        List<UserDTO> usersDTO = new ArrayList<>();
        List<RoleDTO> rolesDTO = roleService.findAll();
        for (int i = 0; i < rolesDTO.size(); i++) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername("username" + (i + 1));
            userDTO.setPassword("password" + (i + 1));
            userDTO.setCreatedBy(System.currentTimeMillis());
            userDTO.setRoleName(rolesDTO.get(i).getRoleName());
            userDTO.setDescription(rolesDTO.get(i).getDescription());
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

}