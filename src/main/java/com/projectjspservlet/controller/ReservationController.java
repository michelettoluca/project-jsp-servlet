package com.projectjspservlet.controller;

import com.projectjspservlet.dao.ReservationDAO;
import com.projectjspservlet.dao.UserDAO;
import com.projectjspservlet.dao.VehicleDAO;
import com.projectjspservlet.entity.Reservation;
import com.projectjspservlet.entity.User;
import com.projectjspservlet.entity.Vehicle;
import com.projectjspservlet.type.ReservationStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet(name = "reservations", value = "/reservations")
public class ReservationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pAction = request.getParameter("action") != null ? request.getParameter("action") : "DEFAULT";

            String dispatchTo = "/reservations";

            switch (pAction) {
                case "CREATE_RESERVATION":
                    System.out.println("CREATE_RESERVATION");
                    dispatchTo += "/create.jsp";

                    getUsers(request, response);
                    getVehicles(request, response);

                    break;

                case "UPDATE_RESERVATION":
                    System.out.println("UPDATE_RESERVATION");
                    dispatchTo += "/update.jsp";
                    String pReservationId = request.getParameter("id");

                    getReservationById(request, response, pReservationId);
                    getUsers(request, response);
                    getVehicles(request, response);

                    break;

                default:
                    System.out.println("DEFAULT");
                    dispatchTo += "/index.jsp";

                    getReservations(request, response);
            }


            RequestDispatcher getRequestDispatcher = request.getRequestDispatcher(dispatchTo);
            getRequestDispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pAction = request.getParameter("action");

            switch (pAction) {
                case "CREATE_RESERVATION":
                    System.out.println("CREATE_RESERVATION");
                    saveReservation(request, response);
                    break;

                case "UPDATE_RESERVATION":
                    System.out.println("UPDATE_RESERVATION");
                    saveReservation(request, response);
                    break;

                case "DELETE_RESERVATION":
                    System.out.println("DELETE_RESERVATION");
                    deleteReservation(request, response);
                    break;

                default:
                    System.out.println("DEFAULT");
            }

            response.sendRedirect("reservations");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getReservations(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Reservation> reservations = ReservationDAO.getReservations();

        request.setAttribute("reservations", reservations);
    }

    private void getReservationById(HttpServletRequest request, HttpServletResponse response, String pReservationId) throws Exception {
        int reservationId = Integer.parseInt(pReservationId);

        Reservation reservation = ReservationDAO.getReservationById(reservationId);

        request.setAttribute("reservation", reservation);
    }

    private void getVehicles(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Vehicle> vehicles = VehicleDAO.getVehicles();

        request.setAttribute("vehicles", vehicles);
    }

    private void getUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> users = UserDAO.getUsers();

        request.setAttribute("users", users);
    }


    private void getVehicleById(HttpServletRequest request, HttpServletResponse response, String pVehicleId) throws Exception {
        int vehicleId = Integer.parseInt(pVehicleId);
        Vehicle vehicle = VehicleDAO.getVehicleById(vehicleId);

        request.setAttribute("vehicle", vehicle);
    }

    private void saveReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pId = request.getParameter("id");
        String pUserId = request.getParameter("userId");
        String pVehicleId = request.getParameter("vehicleId");
        String pBeginsAt = request.getParameter("beginsAt");
        String pEndsAt = request.getParameter("endsAt");
        String pStatus = request.getParameter("status");

        LocalDate beginsAt = LocalDate.parse(pBeginsAt);
        LocalDate endsAt = LocalDate.parse(pEndsAt);

        int userId = Integer.parseInt(pUserId);
        int vehicleId = Integer.parseInt(pVehicleId);

        User user = UserDAO.getUserById(userId);
        Vehicle vehicle = VehicleDAO.getVehicleById(vehicleId);

        Reservation reservation;
        if (pId != null) {
            int id = Integer.parseInt(pId);
            ReservationStatus status = ReservationStatus.valueOf(pStatus);

            reservation = new Reservation(id, user, vehicle, beginsAt, endsAt, status);
        } else {
            reservation = new Reservation(user, vehicle, beginsAt, endsAt);
        }

        ReservationDAO.saveReservation(reservation);
    }

    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pId = request.getParameter("id");
        int id = Integer.parseInt(pId);

        Reservation reservation = ReservationDAO.getReservationById(id);

        ReservationDAO.deleteReservation(reservation);
    }
}
