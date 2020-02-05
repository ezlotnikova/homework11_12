package com.gmail.zlotnikova.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.zlotnikova.service.RoleService;
import com.gmail.zlotnikova.service.impl.RoleServiceImpl;
import com.gmail.zlotnikova.service.model.RoleDTO;

import static com.gmail.zlotnikova.controller.constant.ServletConstant.ROLE_LIST_JSP;

public class RolesServlet extends HttpServlet {

    private RoleService roleService = RoleServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleDTO> roleDTOList = roleService.findAll();
        req.setAttribute("roles", roleDTOList);
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(ROLE_LIST_JSP);
        requestDispatcher.forward(req, resp);
    }

}