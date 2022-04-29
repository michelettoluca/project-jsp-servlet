package com.projectjspservlet.controller;

import com.projectjspservlet.entity.User;
import com.projectjspservlet.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Test", value = "/test")
public class Test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            getUsers(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        RequestDispatcher getRequestDispatcher = request.getRequestDispatcher("/test.jsp");
        getRequestDispatcher.forward(request, response);

    }

    private void getUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> users = UserDAO.getUsers();
        request.setAttribute("users", users);
        }
}
