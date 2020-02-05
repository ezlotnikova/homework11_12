package com.gmail.zlotnikova.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.zlotnikova.service.UserService;
import com.gmail.zlotnikova.service.impl.UserServiceImpl;
import com.gmail.zlotnikova.service.model.UserDTO;

import static com.gmail.zlotnikova.controller.constant.ServletConstant.USER_LIST_JSP;

public class UsersServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDTO> userDTOList = userService.findAll();
        req.setAttribute("users", userDTOList);
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(USER_LIST_JSP);
        requestDispatcher.forward(req, resp);
    }

}