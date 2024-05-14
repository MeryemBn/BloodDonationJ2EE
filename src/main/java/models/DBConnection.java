package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/blooddonation";
    private static final String USERNAME = "root";
    private static final String PASSWORD = ""; 
    private static Connection connection;

    static {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the database connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            // Handle ClassNotFoundException appropriately
            e.printStackTrace();
            // Log or throw a custom exception
        } catch (SQLException e) {
            // Handle SQLException appropriately
            e.printStackTrace();
            // Log or throw a custom exception
        }
    }

    private DBConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            // Handle SQLException appropriately
            e.printStackTrace();
            // Log or throw a custom exception
        }
    }
}
