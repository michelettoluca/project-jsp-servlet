package com.projectjspservlet.servlet;

import com.projectjspservlet.dao.UserDAO;
import com.projectjspservlet.entity.User;
import com.projectjspservlet.type.UserRoles;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "auth", value = "/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pAction = request.getParameter("action");
            if (pAction == null) pAction = "DEFAULT";

            String dispatchTo = "/auth";

            switch (pAction) {
                case "SIGN_IN":
                    System.out.println("SIGN_IN");
                    dispatchTo += "/sign-in.jsp";

                    break;

                case "SIGN_UP":
                    System.out.println("SIGN_UP");
                    dispatchTo += "/sign-up.jsp";

                    break;

                case "SIGN_OUT":
                    System.out.println("SIGN_OUT");
                    signOut(request, response);

                    return;

                default:
                    System.out.println("DEFAULT");
                    dispatchTo += "/index.jsp";
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
                case "SIGN_IN":
                    System.out.println("SIGN_IN");
                    signIn(request, response);
                    break;

                case "SIGN_UP":
                    System.out.println("SIGN_UP");
                    signUp(request, response);
                    break;

                default:
                    System.out.println("DEFAULT");
                    response.sendRedirect("auth");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pFirstName = request.getParameter("firstName");
        String pLastName = request.getParameter("lastName");
        String pUsername = request.getParameter("username");
        String pPassword = request.getParameter("password");

        UserRoles role = UserRoles.CUSTOMER;

        User user = UserDAO.saveUser(new User(pFirstName, pLastName, role, pUsername, pPassword));

        String redirectTo;

        if (user != null) {
            request.getSession().setAttribute("userId", user.getId());
            request.getSession().setAttribute("userRole", user.getRole());

            redirectTo = request.getContextPath();
        } else {
            redirectTo = String.format("auth?action=SIGN_UP&firstName=%s&lastName=%s&error=%s", pFirstName, pLastName, "USERNAME_TAKEN");
        }

        response.sendRedirect(redirectTo);
    }

    private void signIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pUsername = request.getParameter("username");
        String pPassword = request.getParameter("password");

        User user = UserDAO.getUser(pUsername);

        String redirectTo;

        if (user != null) {
            if (user.getPassword().equals(pPassword)) {
                request.getSession().setAttribute("userId", user.getId());
                request.getSession().setAttribute("userRole", user.getRole());

                redirectTo = request.getContextPath();

            } else {
                request.setAttribute("username", user.getUsername());

                redirectTo = String.format("auth?action=SIGN_IN&username=%s&error=%s", pUsername, "INVALID_PASSWORD");
            }
        } else {
            redirectTo = String.format("auth?action=SIGN_IN&error=%s", "INVALID_USERNAME");
        }

        response.sendRedirect(redirectTo);
    }

    private void signOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("userId");
        request.getSession().removeAttribute("userRole");

        response.sendRedirect(request.getContextPath());
    }
}