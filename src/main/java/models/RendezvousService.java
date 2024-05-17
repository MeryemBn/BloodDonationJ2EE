package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class RendezvousService {

    public List<Rendezvous> getRendezvousList() {
        List<Rendezvous> rendezvousList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM rendezvous";
            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    String groupe_sanguin = resultSet.getString("groupe_sanguin");
                    Date date_rendezvous = resultSet.getDate("date_rendezvous");
                    Time heure_rendezvous = resultSet.getTime("heure_rendezvous");
                    rendezvousList.add(new Rendezvous(id, nom, prenom, groupe_sanguin, date_rendezvous, heure_rendezvous));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rendezvousList;
    }

    public boolean insertRendezvous(String nom, String prenom, String groupeSanguin, Date dateRendezvous, Time heureRendezvous) {
        String sql = "INSERT INTO rendezvous (nom, prenom, groupe_sanguin, date_rendezvous, heure_rendezvous) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, groupeSanguin);
            stmt.setDate(4, dateRendezvous);
            stmt.setTime(5, heureRendezvous);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRendezvous(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM rendezvous WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, id);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRendezvous(int id, String nom, String prenom, String groupeSanguin, Date dateRendezvous, Time heureRendezvous) {
        String sql = "UPDATE rendezvous SET nom = ?, prenom = ?, groupe_sanguin = ?, date_rendezvous = ?, heure_rendezvous = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, groupeSanguin);
            preparedStatement.setDate(4, dateRendezvous);
            preparedStatement.setTime(5, heureRendezvous);
            preparedStatement.setInt(6, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
