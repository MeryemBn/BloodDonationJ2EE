package controllers;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.DashBloodGroupService;
import models.DashBloodGroup;
import models.DashAppointmentService;
import models.DashAppointment;
import models.Dashboard;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
	 private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Dashboard model = new Dashboard();
        int donorCount = model.getDonneurCount();
        int bloodGroupCount = model.getPackDisponibleCount();
        int appointmentCount = model.getRendezVousCount();
        int donationCount = model.getHistoriqueDonationCount();

        HttpSession session = request.getSession();
        session.setAttribute("donorCount", donorCount);
        session.setAttribute("bloodGroupCount", bloodGroupCount);
        session.setAttribute("appointmentCount", appointmentCount);
        session.setAttribute("donationCount", donationCount);
        
        DashBloodGroup bloodModel = new DashBloodGroup();
        List<DashBloodGroupService> dataList = bloodModel.getBloodGroupData();
        Gson gson = new Gson();
        String dataListJson = gson.toJson(dataList);

        // Set the JSON string as a request attribute
        request.setAttribute("dataListJson", dataListJson);
        
        DashAppointment appModel = new DashAppointment();
        List<DashAppointmentService> rendezVousList = appModel.getDashAppointments();
        request.setAttribute("rendezVousList", rendezVousList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}