package com.projectjspservlet.controller;

import com.projectjspservlet.dao.ReservationDAO;
import com.projectjspservlet.dao.UserDAO;
import com.projectjspservlet.entity.Reservation;
import com.projectjspservlet.entity.User;
import com.projectjspservlet.type.ReservationStatus;
import com.projectjspservlet.type.UserRoles;
import com.projectjspservlet.utils.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@WebServlet(name = "admin", value = "/admin")
public class AdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean isAllowed = Utils.verifyRole(request, Arrays.asList(UserRoles.ADMIN));

            if (!isAllowed) {
                response.sendRedirect(request.getContextPath() + "?error=UNAUTHORIZED");
                return;
            }

            String pAction = request.getParameter("action");
            if (pAction == null) pAction = "DEFAULT";

            String pUserId = request.getParameter("userId");

            String dispatchTo = "admin";

            System.out.println("GET: " + pAction);
            switch (pAction) {
                case "ADD_CUSTOMER":
                    dispatchTo += "/add-customer.jsp";

                    break;

                case "EDIT_CUSTOMER":
                    dispatchTo += "/edit-customer.jsp";

                    break;

                case "DELETE_CUSTOMER":
                    dispatchTo += "/delete-customer.jsp";
                    break;

                default:
                    dispatchTo += "/index.jsp";

            }

            if (pUserId != null) {
                int userId = Integer.parseInt(pUserId);

                getCustomer(request, response, userId);
                getCustomerReservations(request, response, userId);
            }

            getCustomers(request, response);

            RequestDispatcher getRequestDispatcher = request.getRequestDispatcher(dispatchTo);
            getRequestDispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pAction = request.getParameter("action");
            String pUserId = request.getParameter("userId");

            String redirectTo = "/admin";

            System.out.println("POST: " + pAction);
            switch (pAction) {
                case "ADD_CUSTOMER":
                    addCustomer(request, response);

                    break;

                case "EDIT_CUSTOMER":
                    editCustomer(request, response);

                    break;

                case "DELETE_CUSTOMER":
                    deleteCustomer(request, response);

                    break;

                case "UPDATE_RESERVATION_STATUS":
                    redirectTo += "?userId=" + pUserId;

                    updateReservationStatus(request, response);

                    break;
            }

            response.sendRedirect(request.getContextPath() + redirectTo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void getCustomers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> customers = UserDAO.getCustomers();

        request.setAttribute("customers", customers);
    }

    private void getCustomer(HttpServletRequest request, HttpServletResponse response, int userId) throws Exception {
        User user = UserDAO.getUser(userId);

        if (user != null) request.setAttribute("user", user);
    }

    private void getCustomerReservations(HttpServletRequest request, HttpServletResponse response, int userId) throws Exception {
        List<Reservation> userReservations = ReservationDAO.getUserReservation(userId);
        request.setAttribute("userReservations", userReservations);
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


    private void addCustomer(HttpServletRequest request, HttpServletResponse response) {
        String pFirstName = request.getParameter("firstName");
        String pLastName = request.getParameter("lastName");
        String pUsername = request.getParameter("username");
        String pPassword = request.getParameter("password");

        User newUser = new User(pFirstName, pLastName, UserRoles.CUSTOMER, pUsername, pPassword);

        UserDAO.createUser(newUser);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pId = request.getParameter("id");
        int id = Integer.parseInt(pId);

        User user = UserDAO.getUser(id);

        UserDAO.deleteUser(user);
    }

    private void editCustomer(HttpServletRequest request, HttpServletResponse response) {
        String pId = request.getParameter("id");
        String pFirstName = request.getParameter("firstName");
        String pLastName = request.getParameter("lastName");
        String pRole = request.getParameter("role");
        String pUsername = request.getParameter("username");
        String pPassword = request.getParameter("password");

        int id = Integer.parseInt(pId);

        UserRoles role = pRole != null ? UserRoles.valueOf(pRole) : null;

        UserDAO.updateUser(new User(id, pFirstName, pLastName, role, pUsername, pPassword));
    }

    private void updateReservationStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pReservationId = request.getParameter("reservationId");
        String pReservationStatus = request.getParameter("reservationStatus");

        int id = Integer.parseInt(pReservationId);
        ReservationStatus status = ReservationStatus.valueOf(pReservationStatus);

        ReservationDAO.updateReservationStatus(id, status);
    }
}
