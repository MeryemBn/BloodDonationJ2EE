package models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class DonationService {

	public List<Donation> getAllDonations() {
        List<Donation> donations = new ArrayList<>();
        String query = "SELECT hd.id, hd.groupe_sanguin, hd.donneur, hd.date, hd.heure, hd.qte_donnee, d.nom AS donorName FROM historiquedonation hd JOIN donneur d ON hd.donneur = d.id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Donation donation = new Donation();
                donation.setId(rs.getInt("id"));
                donation.setBloodGroup(rs.getString("groupe_sanguin"));
                donation.setDonorId(rs.getInt("donneur"));
                donation.setDate(rs.getDate("date"));
                donation.setTime(rs.getString("heure"));
                donation.setAmountDonated(rs.getInt("qte_donnee"));
                donation.setDonorName(rs.getString("donorName"));
                donations.add(donation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donations;
    }

	
	public boolean addDonation(Donation donation) {
        String query = "INSERT INTO historiquedonation (groupe_sanguin, donneur, qte_donnee) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, donation.getBloodGroup());
            statement.setInt(2, donation.getDonorId());
            statement.setInt(3, donation.getAmountDonated());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDonation(Donation donation) {
        String query = "UPDATE historiquedonation SET groupe_sanguin=?, donneur=?, qte_donnee=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, donation.getBloodGroup());
            statement.setInt(2, donation.getDonorId());
            statement.setInt(3, donation.getAmountDonated());
            statement.setInt(4, donation.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String getBloodGroupByDonorId(int donorId) {
        String query = "SELECT groupe_sanguin FROM donneur WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, donorId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("groupe_sanguin");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
