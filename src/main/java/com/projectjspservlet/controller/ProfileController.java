package com.projectjspservlet.controller;

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
public class ProfileController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAllowed = Utils.verifyRole(request, Arrays.asList(UserRoles.ADMIN, UserRoles.CUSTOMER));

        if (!isAllowed) {
            response.sendRedirect(request.getContextPath() + "?error=UNAUTHORIZED");
            return;
        }

        String pAction = request.getParameter("action");
        if (pAction == null) pAction = "DEFAULT";

        int userId = (int) request.getSession().getAttribute("userId");

        String dispatchTo = "/profile";

        if ("EDIT_PROFILE".equals(pAction)) {
            System.out.println("EDIT_PROFILE");
            dispatchTo += "/edit-profile.jsp";

        } else {
            System.out.println("DEFAULT");
            dispatchTo += "/index.jsp";

            getProfileData(request, response, userId);
        }

        RequestDispatcher getRequestDispatcher = request.getRequestDispatcher(dispatchTo);
        getRequestDispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pAction = request.getParameter("action");
        if (pAction == null) pAction = "DEFAULT";


        if ("EDIT_PROFILE".equals(pAction)) {
            System.out.println("EDIT_PROFILE");
            editProfile(request, response);
        }


        response.sendRedirect(request.getContextPath() + "/profile");
    }

    private void getProfileData(HttpServletRequest request, HttpServletResponse response, int userId) {
        User user = UserDAO.getUser(userId);
        request.setAttribute("user", user);

        List<Reservation> reservations = ReservationDAO.getUserReservation(userId);
        request.setAttribute("reservations", reservations);
    }

    private void editProfile(HttpServletRequest request, HttpServletResponse response) {
        int id = (int) request.getSession().getAttribute("userId");

        String pFirstName = request.getParameter("firstName");
        String pLastName = request.getParameter("lastName");
        String pRole = request.getParameter("role");
        String pUsername = request.getParameter("username");
        String pPassword = request.getParameter("password");

        UserRoles role = pRole != null ? UserRoles.valueOf(pRole) : null;

        UserDAO.updateUser(new User(id, pFirstName, pLastName, role, pUsername, pPassword));
    }
}


