package com.gmail.zlotnikova.controller.listener;

import java.util.List;
import javax.servlet.ServletContextEvent;

import com.gmail.zlotnikova.service.RoleService;
import com.gmail.zlotnikova.service.TableService;
import com.gmail.zlotnikova.service.UserService;
import com.gmail.zlotnikova.service.helper.generator.RoleGenerator;
import com.gmail.zlotnikova.service.helper.generator.UserGenerator;
import com.gmail.zlotnikova.service.impl.RoleServiceImpl;
import com.gmail.zlotnikova.service.impl.TableServiceImpl;
import com.gmail.zlotnikova.service.impl.UserServiceImpl;
import com.gmail.zlotnikova.service.model.RoleDTO;
import com.gmail.zlotnikova.service.model.UserDTO;

public class InitListener implements javax.servlet.ServletContextListener {

    private TableService tableService = TableServiceImpl.getInstance();
    private RoleService roleService = RoleServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();
    private RoleGenerator roleGenerator = RoleGenerator.getInstance();
    private UserGenerator userGenerator = UserGenerator.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        tableService.createAllTables();
        if (roleService.findAll().isEmpty()) {
            List<RoleDTO> roles = roleGenerator.generateRoles();
            for (RoleDTO roleDTO : roles) {
                roleService.add(roleDTO);
            }
        }
        if (userService.findAll().isEmpty()) {
            List<UserDTO> users = userGenerator.generateUsers();
            for (UserDTO userDTO : users) {
                userService.add(userDTO);
            }
        }
    }

}