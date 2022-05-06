package com.projectjspservlet.servlet;

import com.projectjspservlet.dao.UserDAO;
import com.projectjspservlet.entity.User;
import com.projectjspservlet.type.UserRoles;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "users", value = "/users")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pAction = request.getParameter("action");
            if (pAction == null) pAction = "DEFAULT";

            String dispatchTo;

            System.out.println("GET: " + pAction + " @UserServlet");
            switch (pAction) {
                case "CREATE_USER":
                    dispatchTo = "/users/save.jsp";

                    break;

                case "EDIT_USER":
                    dispatchTo = "/users/save.jsp";

                    String pId = request.getParameter("id");

                    int id = Integer.parseInt(pId);
                    getUser(request, id);

                    break;

                default:
                    dispatchTo = "/users/index.jsp";

                    getUsers(request);
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
            String pOrigin = request.getParameter("origin");
            String pAction = request.getParameter("action");

            System.out.println("POST: " + pAction + " @UserServlet");
            switch (pAction) {
                case "ADD_USER":
                    addUser(request);
                    break;

                case "EDIT_USER":
                    updateUser(request);
                    break;

                case "DELETE_USER":
                    deleteUser(request);
                    break;
            }

            response.sendRedirect(request.getContextPath() + "/" + pOrigin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getUsers(HttpServletRequest request) {
        List<User> users = UserDAO.getUsers();

        request.setAttribute("users", users);
    }

    private void getUser(HttpServletRequest request, int userId) {
        User user = UserDAO.getUser(userId);

        request.setAttribute("user", user);
    }

    private void addUser(HttpServletRequest request) {
        String pFirstName = request.getParameter("firstName");
        String pLastName = request.getParameter("lastName");
        String pRole = request.getParameter("role");
        String pUsername = request.getParameter("username");
        String pPassword = request.getParameter("password");

        UserRoles role = UserRoles.valueOf(pRole);

        User user = new User(pFirstName, pLastName, role, pUsername, pPassword);

        UserDAO.saveUser(user);
    }

    private void updateUser(HttpServletRequest request) {
        String pId = request.getParameter("id");
        String pFirstName = request.getParameter("firstName");
        String pLastName = request.getParameter("lastName");
        String pRole = request.getParameter("role");
        String pUsername = request.getParameter("username");
        String pPassword = request.getParameter("password");

        int id = Integer.parseInt(pId);
        UserRoles role = pRole != null ? UserRoles.valueOf(pRole) : null;

        User user = UserDAO.getUser(id);

        if (user == null) return;

        if (pFirstName != null) user.setFirstName(pFirstName);
        if (pLastName != null) user.setLastName(pLastName);
        if (role != null) user.setRole(role);
        if (pUsername != null) user.setUsername(pUsername);
        if (pPassword != null) user.setPassword(pPassword);

        UserDAO.saveUser(user);
    }

    private void deleteUser(HttpServletRequest request) {
        String pId = request.getParameter("id");

        int id = Integer.parseInt(pId);

        UserDAO.deleteUser(id);
    }
}
