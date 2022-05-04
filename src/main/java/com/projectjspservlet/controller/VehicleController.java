package com.projectjspservlet.controller;

import com.projectjspservlet.dao.VehicleDAO;
import com.projectjspservlet.entity.Vehicle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@WebServlet(name = "vehicles", value = "/vehicles")
public class VehicleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pAction = request.getParameter("action") != null ? request.getParameter("action") : "DEFAULT";
            String pVehicleId = request.getParameter("vehicleId");

            String dispatchTo = "/vehicles";

            switch (pAction) {
                case "CREATE_VEHICLE":
                    System.out.println("CREATE_VEHICLE");
                    dispatchTo += "/create.jsp";

                    break;

                case "UPDATE_VEHICLE":
                    System.out.println("UPDATE_VEHICLE");
                    dispatchTo += "/update.jsp";

                    String pId = request.getParameter("id");
                    getVehicleById(request, response, pId);

                    break;
                    
                default:
                    System.out.println("DEFAULT");
                    dispatchTo += "/index.jsp";

                    if (pVehicleId != null) {
                        getVehicleById(request, response, pVehicleId);
                    }

                    getVehicles(request, response);
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
                case "CREATE_VEHICLE":
                    System.out.println("CREATE_VEHICLE");
                    saveVehicle(request, response);
                    break;

                case "UPDATE_VEHICLE":
                    System.out.println("UPDATE_VEHICLE");
                    saveVehicle(request, response);
                    break;

                case "DELETE_VEHICLE":
                    System.out.println("DELETE_VEHICLE");
                    deleteVehicle(request, response);
                    break;

                default:
                    System.out.println("DEFAULT");
            }

            response.sendRedirect("vehicles");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getVehicles(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Vehicle> vehicles = VehicleDAO.getVehicles();

        request.setAttribute("vehicles", vehicles);
    }

    private void getVehicleById(HttpServletRequest request, HttpServletResponse response, String pVehicleId) throws Exception {
        int vehicleId = Integer.parseInt(pVehicleId);
        Vehicle vehicle = VehicleDAO.getVehicleById(vehicleId);

        request.setAttribute("vehicle", vehicle);
    }

    private void saveVehicle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pId = request.getParameter("id");
        String pBrand = request.getParameter("brand");
        String pModel = request.getParameter("model");
        String pDateOfRegistration = request.getParameter("dateOfRegistration");
        String pPlateNumber = request.getParameter("plateNumber");
        String pType = request.getParameter("type");

        LocalDate dateOfRegistration = LocalDate.parse(pDateOfRegistration);

        Vehicle vehicle;
        if (pId != null) {
            int id = Integer.parseInt(pId);
            vehicle = new Vehicle(id, pBrand, pModel, dateOfRegistration, pPlateNumber, pType);
        } else {
            vehicle = new Vehicle(pBrand, pModel, dateOfRegistration, pPlateNumber, pType);
        }

        VehicleDAO.saveVehicle(vehicle);
    }

    private void deleteVehicle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pId = request.getParameter("id");
        int id = Integer.parseInt(pId);

        Vehicle vehicle = VehicleDAO.getVehicleById(id);

        VehicleDAO.deleteVehicle(vehicle);
    }
}
