package com.gmail.zlotnikova.service.helper.generator;

import java.util.ArrayList;
import java.util.List;

import com.gmail.zlotnikova.service.model.RoleDTO;

public class RoleGenerator {

    private static RoleGenerator instance;

    private RoleGenerator() {
    }

    public static RoleGenerator getInstance() {
        if (instance == null) {
            instance = new RoleGenerator();
        }
        return instance;
    }

    public List<RoleDTO> generateRoles() {
        List<RoleDTO> rolesDTO = new ArrayList<>();
        for (RoleEnum roleModel : RoleEnum.values()) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleName(roleModel.getRoleName());
            roleDTO.setDescription(roleModel.getDescription());
            rolesDTO.add(roleDTO);
        }
        return rolesDTO;
    }

}