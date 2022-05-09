package com.projectjspservlet.servlet;

import com.projectjspservlet.dao.VehicleDAO;
import com.projectjspservlet.entity.Vehicle;
import com.projectjspservlet.type.UserRoles;
import com.projectjspservlet.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebServlet(name = "vehicles", value = "/vehicles")
public class VehicleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            boolean isAllowed = Utils.verifyRole(request, Arrays.asList(UserRoles.ADMIN, UserRoles.CUSTOMER));

            if (!isAllowed) {
                response.sendRedirect(request.getContextPath() + "?error=UNAUTHORIZED");
                return;
            }

            String pAction = request.getParameter("action");
            if (pAction == null) pAction = "DEFAULT";

            String dispatchTo = "/vehicles";

            System.out.println("GET: " + pAction + " @VehicleServlet");
            switch (pAction) {
                case "ADD_VEHICLE":
                    dispatchTo += "/save.jsp";
                    break;

                case "EDIT_VEHICLE":
                    dispatchTo += "/save.jsp";

                    String pId = request.getParameter("id");
                    getVehicle(request, pId);
                    break;

                default:
                    dispatchTo += "/vehicle-list.jsp";

                    getAvailableVehicles(request);
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

            System.out.println("POST: " + pAction + " @VehicleServlet");
            switch (pAction) {
                case "ADD_VEHICLE":
                case "EDIT_VEHICLE":
                    saveVehicle(request);
                    break;

                case "DELETE_VEHICLE":
                    deleteVehicle(request);
                    break;
            }

            response.sendRedirect("vehicles");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getAvailableVehicles(HttpServletRequest request) {
        String pFrom = request.getParameter("from");
        String pTo = request.getParameter("to");

        if (pFrom != null && !pFrom.isEmpty() && pTo != null && !pTo.isEmpty()) {
            LocalDate from = LocalDate.parse(pFrom);
            LocalDate to = LocalDate.parse(pTo);

            long duration = ChronoUnit.DAYS.between(from, to);

            if (duration < 2) return;

            List<Vehicle> vehicles = VehicleDAO.getAvailableVehicles(from, to);

            request.setAttribute("vehicles", vehicles);
        }
    }

    private void getVehicle(HttpServletRequest request, String pVehicleId) {
        int vehicleId = Integer.parseInt(pVehicleId);

        Vehicle vehicle = VehicleDAO.getVehicle(vehicleId);

        request.setAttribute("vehicle", vehicle);
    }

    private void saveVehicle(HttpServletRequest request) {
        String pBrand = request.getParameter("brand");
        String pModel = request.getParameter("model");
        String pDateOfRegistration = request.getParameter("dateOfRegistration");
        String pPlateNumber = request.getParameter("plateNumber");
        String pType = request.getParameter("type");
        String pAction = request.getParameter("action");

        LocalDate dateOfRegistration = LocalDate.parse(pDateOfRegistration);

        Vehicle vehicle;
        if (pAction.equals("EDIT_VEHICLE")) {
            String pId = request.getParameter("id");
            int id = Integer.parseInt(pId);

            vehicle = VehicleDAO.getVehicle(id);

            if (vehicle == null) return;

            if (pBrand != null) vehicle.setBrand(pBrand);
            if (pModel != null) vehicle.setModel(pModel);
            if (dateOfRegistration != null) vehicle.setDateOfRegistration(dateOfRegistration);
            if (pPlateNumber != null) vehicle.setPlateNumber(pPlateNumber);
            if (pType != null) vehicle.setType(pType);
        } else {
            vehicle = new Vehicle(pBrand, pModel, dateOfRegistration, pPlateNumber, pType);
        }

        VehicleDAO.saveVehicle(vehicle);
    }

    private void deleteVehicle(HttpServletRequest request) {
        String pId = request.getParameter("id");

        int id = Integer.parseInt(pId);

        VehicleDAO.deleteVehicle(id);
    }
}
