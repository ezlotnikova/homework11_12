package com.gmail.zlotnikova.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gmail.zlotnikova.controller.validator.Validator;
import com.gmail.zlotnikova.controller.validator.ValidatorImpl;
import com.gmail.zlotnikova.service.RoleService;
import com.gmail.zlotnikova.service.UserService;
import com.gmail.zlotnikova.service.impl.RoleServiceImpl;
import com.gmail.zlotnikova.service.impl.UserServiceImpl;
import com.gmail.zlotnikova.service.model.RoleDTO;
import com.gmail.zlotnikova.service.model.UserDTO;

import static com.gmail.zlotnikova.controller.constant.ServletConstant.CREATING_USER_FAILED_MESSAGE;
import static com.gmail.zlotnikova.controller.constant.ServletConstant.INVALID_DATA_MESSAGE;
import static com.gmail.zlotnikova.controller.constant.ServletConstant.REGISTRATION_JSP;

public class RegistrationServlet extends HttpServlet {

    private RoleService roleService = RoleServiceImpl.getInstance();
    private UserService userService = UserServiceImpl.getInstance();
    private Validator validator = ValidatorImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleDTO> rolesDTO = roleService.findAll();
        req.setAttribute("roles", rolesDTO);
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(REGISTRATION_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        boolean usernameValid = validator.validateUsername(username);
        String password = req.getParameter("password");
        boolean passwordValid = validator.validatePassword(password);
        String roleName = req.getParameter("role");
        boolean roleValid = validator.validateRole(roleName);

        if (!usernameValid || !passwordValid || !roleValid) {
            req.setAttribute("message", INVALID_DATA_MESSAGE);
            doGet(req, resp);
        } else {
            UserDTO userDTO = getUserDTO(req);
            UserDTO newUserDTO = userService.add(userDTO);
            if (newUserDTO.getId() != null) {
                HttpSession session = req.getSession();
                session.setAttribute("username", userDTO.getUsername());
                session.setAttribute("role", userDTO.getRoleName());
                resp.sendRedirect(req.getContextPath());
            } else {
                req.setAttribute("message", CREATING_USER_FAILED_MESSAGE);
                doGet(req, resp);
            }
        }
    }

    private UserDTO getUserDTO(HttpServletRequest req) {
        UserDTO userDTO = new UserDTO();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Long createdBy = System.currentTimeMillis();
        String role = req.getParameter("role");
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setCreatedBy(createdBy);
        userDTO.setRoleName(role);
        return userDTO;
    }

}