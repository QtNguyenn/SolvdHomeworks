package com.project.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/rideservicedb";
    private static final String username = "solvd";
    private static final String password = "password";


    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, username, password);
    }
}    
