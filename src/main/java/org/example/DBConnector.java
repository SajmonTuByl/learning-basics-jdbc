package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String username = "sajmon";
    private static final String password = "sajmon";
    private static final String url = "jdbc:mysql://localhost:3306/sajmon";

    public static Connection connect(){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return connection;
    }
}
