package com.gmail.zlotnikova.controller.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.gmail.zlotnikova.controller.filter.constant.FilterConstant.ADMIN_ROLE;
import static com.gmail.zlotnikova.controller.filter.constant.FilterConstant.LOGIN_URL;
import static com.gmail.zlotnikova.controller.filter.constant.FilterConstant.ROLES_LIST_URL;
import static com.gmail.zlotnikova.controller.filter.constant.FilterConstant.USER_LIST_URL;
import static com.gmail.zlotnikova.controller.filter.constant.FilterConstant.USER_ROLE;

public class LandingFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            res.sendRedirect(req.getContextPath() + LOGIN_URL);
        } else {
            String role = (String) session.getAttribute("role");
            if (role.equals(USER_ROLE)) {
                res.sendRedirect(req.getContextPath() + USER_LIST_URL);
            }
            if (role.equals(ADMIN_ROLE)) {
                res.sendRedirect(req.getContextPath() + ROLES_LIST_URL);
            }
        }
    }

}