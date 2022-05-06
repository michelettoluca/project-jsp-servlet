package com.projectjspservlet.servlet;

import com.projectjspservlet.dao.VehicleDAO;
import com.projectjspservlet.entity.Vehicle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "vehicles", value = "/vehicles")
public class VehicleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pAction = request.getParameter("action") != null ? request.getParameter("action") : "DEFAULT";
            String pVehicleId = request.getParameter("vehicleId");

            String dispatchTo = "/vehicles";

            System.out.println("GET: " + pAction + " @VehicleServlet");
            switch (pAction) {
                case "CREATE_VEHICLE":
                    dispatchTo += "/save.jsp";

                    break;

                case "UPDATE_VEHICLE":
                    dispatchTo += "/update.jsp";

                    String pId = request.getParameter("id");
                    getVehicle(request, pId);

                    break;

                default:
                    dispatchTo += "/index.jsp";

                    if (pVehicleId != null) {
                        getVehicle(request, pVehicleId);
                    }

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
                    addVehicle(request);

                    break;

                case "EDIT_VEHICLE":
                    updateVehicle(request);

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

        if (pFrom != null && pTo != null) {
            LocalDate from = LocalDate.parse(pFrom);
            LocalDate to = LocalDate.parse(pTo);
            
            List<Vehicle> vehicles = VehicleDAO.getVehicles();

            request.setAttribute("vehicles", new ArrayList<>());
        }


    }

    private void getVehicle(HttpServletRequest request, String pVehicleId) {
        int vehicleId = Integer.parseInt(pVehicleId);

        Vehicle vehicle = VehicleDAO.getVehicle(vehicleId);

        request.setAttribute("vehicle", vehicle);
    }

    private void addVehicle(HttpServletRequest request) {
        String pBrand = request.getParameter("brand");
        String pModel = request.getParameter("model");
        String pDateOfRegistration = request.getParameter("dateOfRegistration");
        String pPlateNumber = request.getParameter("plateNumber");
        String pType = request.getParameter("type");

        LocalDate dateOfRegistration = LocalDate.parse(pDateOfRegistration);

        VehicleDAO.saveVehicle(new Vehicle(pBrand, pModel, dateOfRegistration, pPlateNumber, pType));
    }

    private void updateVehicle(HttpServletRequest request) {
        String pId = request.getParameter("id");
        String pBrand = request.getParameter("brand");
        String pModel = request.getParameter("model");
        String pDateOfRegistration = request.getParameter("dateOfRegistration");
        String pPlateNumber = request.getParameter("plateNumber");
        String pType = request.getParameter("type");

        int id = Integer.parseInt(pId);
        LocalDate dateOfRegistration = pDateOfRegistration != null ? LocalDate.parse(pDateOfRegistration) : null;

        Vehicle vehicle = VehicleDAO.getVehicle(id);

        if (pBrand != null) vehicle.setBrand(pBrand);
        if (pModel != null) vehicle.setModel(pModel);
        if (dateOfRegistration != null) vehicle.setDateOfRegistration(dateOfRegistration);
        if (pPlateNumber != null) vehicle.setPlateNumber(pPlateNumber);
        if (pType != null) vehicle.setType(pType);

        VehicleDAO.saveVehicle(vehicle);
    }

    private void deleteVehicle(HttpServletRequest request) {
        String pId = request.getParameter("id");

        int id = Integer.parseInt(pId);

        VehicleDAO.deleteVehicle(id);
    }
}
