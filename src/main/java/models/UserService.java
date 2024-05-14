package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {
    public boolean registerUser(User user) throws ClassNotFoundException{
    	Connection connection = DBConnection.getConnection();
        PreparedStatement statement = null;
        boolean isSuccess = false;

        try {
            // Obtain database connection from DBConnection class
        	

            // Prepare SQL statement
            String sql = "INSERT INTO donneur (nom, groupe_sanguin, sex, age, adresse, numtel, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getBloodGroup());
            statement.setString(3, user.getGender());
            statement.setInt(4, user.getAge());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getPhoneNumber());
            statement.setString(7, user.getUsername());
            statement.setString(8, user.getPassword());

            // Execute SQL statement
            int rowsAffected = statement.executeUpdate();

            // Check if registration was successful
            isSuccess = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isSuccess;
    }
}
