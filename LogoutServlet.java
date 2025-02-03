package com.tap.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the current session, but don't create a new one if it doesn't exist
        HttpSession session = request.getSession(false); // Get session if exists, don't create new
        if (session != null) {
            session.invalidate(); // Invalidate the session and remove all data
        }

        // Redirect to home page after logging out
        response.sendRedirect("home.jsp");
    }
}
