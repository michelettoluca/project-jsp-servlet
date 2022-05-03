package com.projectjspservlet.controller;

import com.projectjspservlet.dao.UserDAO;
import com.projectjspservlet.entity.User;
import com.projectjspservlet.type.UserActions;
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
            String pUserId = request.getParameter("userId");

            if (pUserId != null) {
                getUserById(request, response, pUserId);
            }

            getUsers(request, response);

            RequestDispatcher getRequestDispatcher = request.getRequestDispatcher("/users/index.jsp");
            getRequestDispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pAction = request.getParameter("action");

            UserActions action = pAction != null ? UserActions.valueOf(pAction) : UserActions.UNDEFINED;

            switch (action) {
                case CREATE_USER:
                    System.out.println("CREATE_USER");
                    createUser(request, response);
                    break;

                case UPDATE_USER:
                    System.out.println("UPDATE_USER");
                    updateUser(request, response);
                    break;

                case DELETE_USER:
                    System.out.println("DELETE_USER");
                    deleteUser(request, response);
                    break;

                default:
                    System.out.println("UNDEFINED");
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
        User user = UserDAO.getUserById(userId);

        if (user != null) {
            request.setAttribute("user", user);
        } else {
            request.setAttribute("user", null);
        }
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        UserRoles role = UserRoles.valueOf(request.getParameter("role"));

        User newUser = new com.projectjspservlet.entity.User(firstName, lastName, role);

        UserDAO.saveUser(newUser);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        UserRoles role = UserRoles.valueOf(request.getParameter("role"));

        User newUser = new com.projectjspservlet.entity.User(id, firstName, lastName, role);

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
}
