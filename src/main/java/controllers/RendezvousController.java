package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.google.gson.Gson;

import models.Rendezvous;
import models.RendezvousService;


/**
 * Servlet implementation class RendezvousController
 */
public class RendezvousController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RendezvousService rendezvousService;

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
        response.getWriter().write(jsonData);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String groupeSanguin = request.getParameter("groupeSanguin");
        Date dateRendezvous = Date.valueOf(request.getParameter("dateRendezvous"));
        Time heureRendezvous = Time.valueOf(request.getParameter("heureRendezvous"));

        boolean success = rendezvousService.insertRendezvous(nom, prenom, groupeSanguin, dateRendezvous, heureRendezvous);
        response.setStatus(success ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_BAD_REQUEST);
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

}