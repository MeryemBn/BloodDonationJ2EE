package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class Appointement {
    private String nom;
    private String prenom;
    private String groupeSanguin;
    private Date dateRendezvous;
    private Time heureRendezvous;

    public Appointement(String nom, String prenom, String groupeSanguin, Date dateRendezvous, Time heureRendezvous) {
        this.nom = nom;
        this.prenom = prenom;
        this.groupeSanguin = groupeSanguin;
        this.dateRendezvous = dateRendezvous;
        this.heureRendezvous = heureRendezvous;
    }

    public boolean save() {
        String sql = "INSERT INTO rendezvous (nom, prenom, groupe_sanguin, date_rendezvous, heure_rendezvous) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); // Use DBConnection for obtaining connection
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, this.nom);
            stmt.setString(2, this.prenom);
            stmt.setString(3, this.groupeSanguin);
            stmt.setDate(4, this.dateRendezvous);
            stmt.setTime(5, this.heureRendezvous);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
