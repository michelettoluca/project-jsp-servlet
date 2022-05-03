package com.projectjspservlet.controller;

import com.projectjspservlet.dao.ReservationDAO;
import com.projectjspservlet.dao.UserDAO;
import com.projectjspservlet.entity.Reservation;
import com.projectjspservlet.entity.User;
import com.projectjspservlet.type.AdminActions;
import com.projectjspservlet.type.UserRoles;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "admin", value = "/admin")
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pUserId = request.getParameter("userId");

            if (pUserId != null) {
                getUserById(request, response, pUserId);
            }

            getCustomers(request, response);
            getReservations(request, response);
            getPendingReservations(request, response);

            RequestDispatcher getRequestDispatcher = request.getRequestDispatcher("/admin/index.jsp");
            getRequestDispatcher.forward(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pAction = request.getParameter("action");
            String pUserId = request.getParameter("userId");

            AdminActions action = pAction != null ? AdminActions.valueOf(pAction) : AdminActions.UNDEFINED;

            String redirectTo = "/admin";

            switch (action) {
                case CREATE_USER:
                    System.out.println("Action: CREATE_USER");
                    createUser(request, response);
                    break;

                case UPDATE_USER:
                    System.out.println("Action: UPDATE_USER");
//                    updateUser(request, response);
                    break;

                case DELETE_USER:
                    System.out.println("Action: DELETE_USER");
                    deleteUser(request, response);
                    break;

                case APPROVE_RESERVATION:
                    System.out.println("Action: APPROVE_RESERVATION");
                    redirectTo += "?userId=" + pUserId;
//                    approveReservation(request, response);
                    break;

                case DENY_RESERVATION:
                    System.out.println("Action: DENY_RESERVATION");
                    redirectTo += "?userId=" + pUserId;
//                    denyReservation(request, response);
                    break;

                default:
                    System.out.println("Action: UNDEFINED");
            }

            response.sendRedirect(request.getContextPath() + redirectTo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        UserRoles role = UserRoles.valueOf(request.getParameter("role"));

        User newUser = new com.projectjspservlet.entity.User(firstName, lastName, role);

        UserDAO.saveUser(newUser);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        UserRoles role = UserRoles.valueOf(request.getParameter("role"));

        User user = new User(id, firstName, lastName, role);

        UserDAO.deleteUser(user);
    }

    private void getCustomers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> customers = UserDAO.getCustomers();

        request.setAttribute("customers", customers);
    }

    private void getUserById(HttpServletRequest request, HttpServletResponse response, String pUserId) throws Exception {
        int userId = Integer.parseInt(pUserId);
        User user = UserDAO.getUserById(userId);

        if (user != null) {
            request.setAttribute("user", user);
        }
    }

    private void getReservations(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Reservation> reservations = ReservationDAO.getReservations();

        request.setAttribute("reservations", reservations);
    }

    private void getPendingReservations(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Reservation> reservations = ReservationDAO.getPendingReservations();

        request.setAttribute("pendingReservations", reservations);
    }

    private void getApprovedReservations(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Reservation> reservations = ReservationDAO.getApprovedReservations();

        request.setAttribute("approvedReservations", reservations);
    }
}
