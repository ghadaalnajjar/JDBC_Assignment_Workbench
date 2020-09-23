package org.example.dbConnection;

import org.example.exception.MySQLConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static String URL = "jdbc:mysql://localhost:3306/todoit?&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin";
    private static String USERNAME = "root";
    private static String PASSWORD = "Ghada1981";

    public static Connection mySQLGetConnection() throws MySQLConnectionException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MySQLConnectionException(e.getMessage());
        }
        return connection;
    }
}
