package com.projectjspservlet.controller;

import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "home", value = "/home")
public class HelloServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext context = getServletContext();
        String projectName = context.getInitParameter("project-name");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>" + projectName + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}