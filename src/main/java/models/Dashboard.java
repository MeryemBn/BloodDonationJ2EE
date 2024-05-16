package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dashboard {
    // Get count for 'donneur' table
    public int getDonneurCount() {
        return getCount("SELECT COUNT(*) FROM donneur");
    }

    // Get count for 'historiquedonation' table
    public int getHistoriqueDonationCount() {
        return getCount("SELECT COUNT(*) FROM historiquedonation");
    }

    // Get count for 'pack_disponible' table
    public int getPackDisponibleCount() {
        return getCount("SELECT COUNT(*) FROM pack_disponible");
    }

    // Get count for 'rendezvous' table
    public int getRendezVousCount() {
        return getCount("SELECT COUNT(*) FROM rendezvous");
    }

    // Method to execute count query
    private int getCount(String query) {
        int count = 0;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return count;
    }
}
