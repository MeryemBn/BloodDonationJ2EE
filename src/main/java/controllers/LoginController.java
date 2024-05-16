package controllers;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Login;
import models.LoginService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userType = request.getParameter("user-type");

        // Create Login object
        Login login = new Login(username, password, userType);

        // Call a service method to handle user login
        LoginService loginService = new LoginService();
        String loginStatus = loginService.validateLogin(login);

        // Forward to appropriate view based on login status and user type
        if (loginStatus.equals("success")) {
            // Successful login
            request.getSession().setAttribute("user", login);
            if (userType.equals("admin")) {
                // Redirect to Admin.jsp for admin user
            	response.sendRedirect(request.getContextPath() + "/dashboard");
            } else if (userType.equals("donor")) {
                // Redirect to index.jsp for donor user
                response.sendRedirect("index.jsp");
            } else {
                // Handle other user types
                PrintWriter out = response.getWriter();
                out.println("User type not recognized");
            }
        } else {
            // Failed login
            request.setAttribute("loginError", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
    }
}
