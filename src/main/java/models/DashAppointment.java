package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DashAppointment {
    public List<DashAppointmentService> getDashAppointments() {
        List<DashAppointmentService> rendezVousList = new ArrayList<>();

        // Get current date and time
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Connexion à la base de données et récupération des rendez-vous de la semaine
        try (Connection conn = DBConnection.getConnection()) {
            int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
            String query = "SELECT nom, prenom, groupe_sanguin, date_rendezvous, heure_rendezvous FROM rendezvous WHERE WEEKOFYEAR(date_rendezvous) = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, currentWeek);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String groupeSanguin = rs.getString("groupe_sanguin");
                Date dateRendezVous = rs.getDate("date_rendezvous"); // Get date as Date
                String heureRendezVous = rs.getString("heure_rendezvous");

                // Combine date and time to create the full appointment datetime
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String fullAppointmentDateTimeString = dateRendezVous.toString() + " " + heureRendezVous;
                Date fullAppointmentDateTime = dateTimeFormat.parse(fullAppointmentDateTimeString);

                // Calculate status based on whether the appointment datetime has passed
                String status = (fullAppointmentDateTime.before(currentDate)) ? "done" : "pending";

                rendezVousList.add(new DashAppointmentService(nom, prenom, groupeSanguin, dateRendezVous, heureRendezVous, status));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return rendezVousList;
    }
}
