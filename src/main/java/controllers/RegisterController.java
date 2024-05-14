package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import models.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class RegisterController
 */

@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("full-name");
        String bloodGroup = request.getParameter("bloodgroup");
        String gender = request.getParameter("gender");
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Create User object
        User user = new User(name, bloodGroup, gender, age, address, phoneNumber, username, password);

        // Call a service or DAO method to handle user registration
        UserService userService = new UserService();
        boolean registrationStatus = false;
        try {
            registrationStatus = userService.registerUser(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exception appropriately, e.g., log it
            // Set appropriate error message or redirect to an error page
            request.setAttribute("status", "error");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Forward to appropriate view based on registration status
        if (registrationStatus) {
            request.setAttribute("status", "success");
        } else {
            request.setAttribute("status", "failed");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
        dispatcher.forward(request, response);
    }
}
