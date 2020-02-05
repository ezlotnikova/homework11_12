package com.gmail.zlotnikova.controller.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gmail.zlotnikova.util.PropertyUtil;

import static com.gmail.zlotnikova.controller.filter.constant.FilterConstant.ACCESS_DENIED_MESSAGE;
import static com.gmail.zlotnikova.controller.filter.constant.FilterConstant.ADMIN_PERMISSION_FILE;
import static com.gmail.zlotnikova.controller.filter.constant.FilterConstant.ADMIN_ROLE;
import static com.gmail.zlotnikova.controller.filter.constant.FilterConstant.USER_PERMISSION_FILE;
import static com.gmail.zlotnikova.controller.filter.constant.FilterConstant.USER_ROLE;

public class AccessFilter extends HttpFilter {

    private PropertyUtil propertyUtil = PropertyUtil.getInstance();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("role") == null) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, ACCESS_DENIED_MESSAGE);
        } else {
            String role = (String) session.getAttribute("role");
            String page = req.getRequestURI().substring(req.getContextPath().length());
            String propertyFileName = "";
            if (role.equals(ADMIN_ROLE)) {
                propertyFileName = ADMIN_PERMISSION_FILE;
            } else if (role.equals(USER_ROLE)) {
                propertyFileName = USER_PERMISSION_FILE;
            }
            boolean accessGranted = Boolean.parseBoolean(propertyUtil.getPropertyFromFile(propertyFileName, page));
            if (accessGranted) {
                chain.doFilter(req, res);
            } else {
                res.sendError(HttpServletResponse.SC_FORBIDDEN, ACCESS_DENIED_MESSAGE);
            }
        }
    }

}