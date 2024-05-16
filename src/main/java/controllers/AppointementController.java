package controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Appointement;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

public class AppointementController extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String groupeSanguin = request.getParameter("groupe_sanguin");
        Date dateRendezvous = Date.valueOf(request.getParameter("date_rendezvous"));
        
        // Validate and format the time input
        String heureRendezvousStr = request.getParameter("heure_rendezvous");
        Time heureRendezvous;
        try {
            heureRendezvous = Time.valueOf(heureRendezvousStr + ":00");
        } catch (IllegalArgumentException e) {
            request.setAttribute("message", "Invalid time format. Please use the format hh:mm.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        Appointement rendezvous = new Appointement(nom, prenom, groupeSanguin, dateRendezvous, heureRendezvous);

        boolean success = rendezvous.save();
        if (success) {
            request.setAttribute("message", "Your appointment has been successfully inserted!");
        } else {
            request.setAttribute("message", "Failed to insert the appointment.");
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
