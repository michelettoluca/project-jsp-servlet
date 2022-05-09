package com.projectjspservlet.servlet;

import com.projectjspservlet.dao.ReservationDAO;
import com.projectjspservlet.dao.UserDAO;
import com.projectjspservlet.entity.Reservation;
import com.projectjspservlet.entity.User;
import com.projectjspservlet.type.UserRoles;
import com.projectjspservlet.utils.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "profile", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAllowed = Utils.verifyRole(request, Arrays.asList(UserRoles.ADMIN, UserRoles.CUSTOMER));

        if (!isAllowed) {
            response.sendRedirect(request.getContextPath() + "?error=UNAUTHORIZED");
            return;
        }

        int userId = (int) request.getSession().getAttribute("userId");

        getProfileData(request, userId);

        RequestDispatcher getRequestDispatcher = request.getRequestDispatcher("/profile/profile.jsp");
        getRequestDispatcher.forward(request, response);
    }


    private void getProfileData(HttpServletRequest request, int userId) {
        User user = UserDAO.getUser(userId);
        
        request.setAttribute("user", user);
    }
}


