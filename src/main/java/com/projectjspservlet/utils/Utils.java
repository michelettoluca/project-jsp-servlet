package com.projectjspservlet.utils;

import com.projectjspservlet.dao.UserDAO;
import com.projectjspservlet.entity.User;
import com.projectjspservlet.type.UserRoles;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

public class Utils {
    public static boolean verifyRole(HttpServletRequest request, List<UserRoles> allowedRoles) {
        Object sUserId = request.getSession().getAttribute("userId");

        if (sUserId == null) return false;

        int userId = (int) sUserId;

        User userMatch = UserDAO.getUser(userId);
        if (userMatch == null) return false;

        return allowedRoles.contains(userMatch.getRole());
    }
}