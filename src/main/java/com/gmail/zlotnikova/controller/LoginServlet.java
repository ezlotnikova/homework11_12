package com.gmail.zlotnikova.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gmail.zlotnikova.service.UserService;
import com.gmail.zlotnikova.service.impl.UserServiceImpl;
import com.gmail.zlotnikova.service.model.UserDTO;

import static com.gmail.zlotnikova.controller.constant.ServletConstant.LOGIN_FAIL_MESSAGE;
import static com.gmail.zlotnikova.controller.constant.ServletConstant.LOGIN_JSP;

public class LoginServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(LOGIN_JSP);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDTO userDTO = userService.findUser(username, password);
        if (userDTO != null) {
            HttpSession session = req.getSession();
            session.setAttribute("username", userDTO.getUsername());
            session.setAttribute("role", userDTO.getRoleName());
            resp.sendRedirect(req.getContextPath());
        } else {
            req.setAttribute("message", LOGIN_FAIL_MESSAGE);
            doGet(req, resp);
        }
    }

}