package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    public String validateLogin(Login login) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String result = "failed";

        try {
            // Obtain database connection from DBConnection class
            Connection connection = DBConnection.getConnection();

            // Prepare SQL statement based on user type
            String sql = "";
            if (login.getUserType().equals("donor")) {
                sql = "SELECT * FROM donneur WHERE username = ? AND password = ?";
            } else if (login.getUserType().equals("admin")) {
                sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
            }

            // Execute SQL statement
	            statement = connection.prepareStatement(sql);
            statement.setString(1, login.getUsername());
            statement.setString(2, login.getPassword());
            resultSet = statement.executeQuery();

            // Check if user exists in the database
            if (resultSet.next()) {
                result = "success";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } /*finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/

        return result;
    }
}
