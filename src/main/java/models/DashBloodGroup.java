package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashBloodGroup {
    public List<DashBloodGroupService> getBloodGroupData() {
        List<DashBloodGroupService> dataList = new ArrayList<>();

        // Connexion à la base de données et récupération des données
        try {
        	Connection conn = DBConnection.getConnection();
        	String query = "SELECT groupe_sanguin, SUM(qte) AS total FROM pack_disponible GROUP BY groupe_sanguin";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String groupeSanguin = rs.getString("groupe_sanguin");
                int qte = rs.getInt("total");
                dataList.add(new DashBloodGroupService(groupeSanguin, qte));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
