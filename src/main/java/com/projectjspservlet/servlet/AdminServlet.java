package com.projectjspservlet.servlet;

import com.projectjspservlet.dao.UserDAO;
import com.projectjspservlet.entity.User;
import com.projectjspservlet.type.UserRoles;
import com.projectjspservlet.utils.Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@WebServlet(name = "admin", value = "/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean isAllowed = Utils.verifyRole(request, Arrays.asList(UserRoles.ADMIN));

            if (!isAllowed) {
                response.sendRedirect(request.getContextPath() + "?error=UNAUTHORIZED");
                return;
            }

            getCustomers(request, response);

            RequestDispatcher getRequestDispatcher = request.getRequestDispatcher("admin/index.jsp");
            getRequestDispatcher.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getCustomers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> customers = UserDAO.getCustomers();

        request.setAttribute("customers", customers);


        String pUserId = request.getParameter("userId");

        if (pUserId != null) {
            int userId = Integer.parseInt(pUserId);

            for (User customer : customers) {
                if (customer.getId() == userId) {
                    request.setAttribute("user", customer);
                    break;
                }
            }
        }

    }
}
