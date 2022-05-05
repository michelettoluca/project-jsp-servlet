package com.projectjspservlet.controller;

import com.projectjspservlet.dao.UserDAO;
import com.projectjspservlet.entity.User;
import com.projectjspservlet.type.UserRoles;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "users", value = "/users")
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pAction = request.getParameter("action") != null ? request.getParameter("action") : "DEFAULT";
            String pUserId = request.getParameter("userId");

            String dispatchTo = "/users";

            switch (pAction) {
                case "CREATE_USER":
                    System.out.println("CREATE_USER");
                    dispatchTo += "/create.jsp";

                    break;

                case "UPDATE_USER":
                    System.out.println("UPDATE_USER");
                    dispatchTo += "/update.jsp";

                    String pId = request.getParameter("id");
                    getUserById(request, response, pId);

                    break;

                default:
                    System.out.println("DEFAULT");
                    dispatchTo += "/index.jsp";

                    if (pUserId != null) {
                        getUserById(request, response, pUserId);
                    }

                    getUsers(request, response);
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
                case "CREATE_USER":
                    System.out.println("CREATE_USER");
                    createUser(request, response);
                    break;

                case "UPDATE_USER":
                    System.out.println("UPDATE_USER");
                    updateUser(request, response);
                    break;

                case "DELETE_USER":
                    System.out.println("DELETE_USER");
                    deleteUser(request, response);
                    break;

                default:
                    System.out.println("DEFAULT");
            }

            response.sendRedirect(request.getContextPath() + "/users");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> users = UserDAO.getUsers();

        request.setAttribute("users", users);
    }

    private void getUserById(HttpServletRequest request, HttpServletResponse response, String pUserId) throws Exception {
        int userId = Integer.parseInt(pUserId);
        
        User user = UserDAO.getUser(userId);

        request.setAttribute("user", user);
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pFirstName = request.getParameter("firstName");
        String pLastName = request.getParameter("lastName");
        String pRole = request.getParameter("role");
        String pPassword = request.getParameter("password");
        String pUsername = request.getParameter("username");

        UserRoles role = UserRoles.valueOf(pRole);

        User newUser = new User(pFirstName, pLastName, role, pPassword, pUsername);

        UserDAO.saveUser(newUser);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pId = request.getParameter("id");
        String pFirstName = request.getParameter("firstName");
        String pLastName = request.getParameter("lastName");
        String pRole = request.getParameter("role");
        String pUsername = request.getParameter("username");
        String pPassword = request.getParameter("password");

        int id = Integer.parseInt(pId);

        User user = UserDAO.getUser(id);

        if (pFirstName != null) user.setFirstName(pFirstName);

        if (pLastName != null) user.setLastName(pLastName);

        if (pRole != null) {
            UserRoles role = UserRoles.valueOf(pRole);
            user.setRole(role);
        }

        if (pUsername != null) user.setUsername(pUsername);

        if (pPassword != null) user.setPassword(pPassword);

        UserDAO.saveUser(user);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pId = request.getParameter("id");

        int id = Integer.parseInt(pId);

        UserDAO.deleteUser(id);
    }
}
