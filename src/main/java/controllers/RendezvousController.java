package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import models.Rendezvous;
import models.RendezvousService;


/**
 * Servlet implementation class RendezvousController
 */
@WebServlet("/BloodDonation/RendezvousController")
public class RendezvousController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RendezvousService rendezvousService = new RendezvousService();

    @Override
    public void init() throws ServletException {
        super.init();
        rendezvousService = new RendezvousService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Rendezvous> rendezvousList = rendezvousService.getRendezvousList();
        Gson gson = new Gson();
		String jsonData = gson.toJson(rendezvousList);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(jsonData);
		out.flush();
    }

  

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        Gson gson = new Gson();
        
        try {
            JsonObject json = gson.fromJson(reader, JsonObject.class);
            
            String nom = json.get("nom").getAsString();
            String prenom = json.get("prenom").getAsString();
            String groupeSanguin = json.get("groupe_sanguin").getAsString();
            String dateRendezvousStr = json.get("date_rendezvous").getAsString();
            String heureRendezvousStr = json.get("heure_rendezvous").getAsString();
            
            // Parse date and time strings to appropriate types
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateRendezvous = new Date(dateFormat.parse(dateRendezvousStr).getTime());
            Time heureRendezvous = Time.valueOf(heureRendezvousStr);

            // Call service method to save rendezvous
            boolean success = rendezvousService.insertRendezvous(nom, prenom, groupeSanguin, dateRendezvous, heureRendezvous);
            
            // Set response status based on success
            if (success) {
                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Failed to create rendezvous");
            }
        } catch (JsonSyntaxException | ParseException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid data format");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Internal server error");
        } finally {
            reader.close();
        }
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String groupeSanguin = request.getParameter("groupeSanguin");
        Date dateRendezvous = Date.valueOf(request.getParameter("dateRendezvous"));
        Time heureRendezvous = Time.valueOf(request.getParameter("heureRendezvous"));

        boolean success = rendezvousService.updateRendezvous(id, nom, prenom, groupeSanguin, dateRendezvous, heureRendezvous);
        response.setStatus(success ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
    }


    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        boolean success = rendezvousService.deleteRendezvous(id);
        response.setStatus(success ? HttpServletResponse.SC_NO_CONTENT : HttpServletResponse.SC_BAD_REQUEST);
    }
    
    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return dateFormat.format(date);
    }

    private String formatTime(Time time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        return timeFormat.format(time);
    }

}