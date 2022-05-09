package com.projectjspservlet.servlet;

import com.projectjspservlet.dao.ReservationDAO;
import com.projectjspservlet.dao.UserDAO;
import com.projectjspservlet.dao.VehicleDAO;
import com.projectjspservlet.entity.Reservation;
import com.projectjspservlet.entity.User;
import com.projectjspservlet.entity.Vehicle;
import com.projectjspservlet.type.ReservationStatus;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@WebServlet(name = "reservations", value = "/reservations")
public class ReservationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pAction = request.getParameter("action");
            String pUserId = request.getParameter("userId");

            String redirectTo = "vehicles";

            System.out.println("POST: " + pAction + " @ReservationServlet");
            switch (pAction) {
                case "ADD_RESERVATION":
                    saveReservation(request);
                    break;

                case "EDIT_RESERVATION":
                    redirectTo = "admin?userId=" + pUserId;

                    saveReservation(request);
                    break;

                case "DELETE_RESERVATION":
                    deleteReservation(request);
                    break;
            }

            response.sendRedirect(redirectTo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void saveReservation(HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("userId");

        String pAction = request.getParameter("action");
        String pVehicleId = request.getParameter("vehicleId");
        String pBeginsAt = request.getParameter("beginsAt");
        String pEndsAt = request.getParameter("endsAt");
        String pStatus = request.getParameter("status");

        LocalDate beginsAt = pBeginsAt != null ? LocalDate.parse(pBeginsAt) : null;
        LocalDate endsAt = pEndsAt != null ? LocalDate.parse(pEndsAt) : null;
        ReservationStatus status = pStatus != null ? ReservationStatus.valueOf(pStatus) : null;

        if (beginsAt != null && endsAt != null) {
            long duration = ChronoUnit.DAYS.between(beginsAt, endsAt);

            if (duration < 2) return;
        }

        Reservation reservation;
        if (pAction.equals("EDIT_RESERVATION")) {
            String pId = request.getParameter("id");
            int id = Integer.parseInt(pId);

            reservation = ReservationDAO.getReservation(id);

            if (reservation == null) return;

            if (beginsAt != null) reservation.setBeginsAt(beginsAt);
            if (endsAt != null) reservation.setEndsAt(endsAt);
            if (status != null) reservation.setStatus(status);

        } else {
            int vehicleId = Integer.parseInt(pVehicleId);
            User user = UserDAO.getUser(userId);
            Vehicle vehicle = VehicleDAO.getVehicle(vehicleId);

            reservation = new Reservation(user, vehicle, beginsAt, endsAt, ReservationStatus.PENDING);
        }

        ReservationDAO.saveReservation(reservation);
    }

    private void deleteReservation(HttpServletRequest request) {
        String pId = request.getParameter("id");

        int id = Integer.parseInt(pId);

        ReservationDAO.deleteReservation(id);
    }
}
