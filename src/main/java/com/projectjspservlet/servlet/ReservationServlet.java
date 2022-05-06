package com.projectjspservlet.servlet;

import com.projectjspservlet.dao.ReservationDAO;
import com.projectjspservlet.dao.UserDAO;
import com.projectjspservlet.dao.VehicleDAO;
import com.projectjspservlet.entity.Reservation;
import com.projectjspservlet.entity.User;
import com.projectjspservlet.entity.Vehicle;
import com.projectjspservlet.type.ReservationStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "reservations", value = "/reservations")
public class ReservationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pAction = request.getParameter("action");
            if (pAction == null) pAction = "DEFAULT";

            String pVehicleId = request.getParameter("vehicleId");

            String dispatchTo;

            System.out.println("GET: " + pAction + " @ReservationServlet");
            switch (pAction) {
                case "ADD_RESERVATION":
                case "EDIT_RESERVATION":
                    dispatchTo = "/reservations/save.jsp";
//                    getVehicle(request, response, pVehicleId);
                    break;

                default:
                    dispatchTo = "/reservations/index.jsp";

                    getReservations(request);
            }

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


            System.out.println("POST: " + pAction + " @ReservationServlet");
            switch (pAction) {
                case "ADD_RESERVATION":
                    addReservation(request);

                    break;

                case "EDIT_RESERVATION":
//                    editReservation(request);
                    break;

                case "DELETE_RESERVATION":
                    deleteReservation(request);
                    break;

            }

            response.sendRedirect("reservations");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getReservations(HttpServletRequest request) {
        List<Reservation> reservations = ReservationDAO.getReservations();

        request.setAttribute("reservations", reservations);
    }

    private void getReservation(HttpServletRequest request, String pReservationId) {
        int reservationId = Integer.parseInt(pReservationId);

        Reservation reservation = ReservationDAO.getReservation(reservationId);

        request.setAttribute("reservation", reservation);
    }

    private void addReservation(HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("userId");

        String pVehicleId = request.getParameter("vehicleId");
        String pBeginsAt = request.getParameter("beginsAt");
        String pEndsAt = request.getParameter("endsAt");

        int vehicleId = Integer.parseInt(pVehicleId);
        LocalDate beginsAt = LocalDate.parse(pBeginsAt);
        LocalDate endsAt = LocalDate.parse(pEndsAt);

        User user = UserDAO.getUser(userId);
        Vehicle vehicle = VehicleDAO.getVehicle(vehicleId);

        Reservation reservation = new Reservation(user, vehicle, beginsAt, endsAt, ReservationStatus.PENDING);

        ReservationDAO.saveReservation(reservation);
    }

    private void deleteReservation(HttpServletRequest request) {
        String pId = request.getParameter("id");
        int id = Integer.parseInt(pId);

        ReservationDAO.deleteReservation(id);
    }
}
