package org.example;

import java.sql.*;
import java.util.Scanner;

/*

Database "sajmon" consists of a table "users", in which there are 4 columns:
username (String), age (int), city (String), timestamp (time)

*/

public class DBOperations {

    // Method that executes an SQL query and returns a ResultSet
    private static ResultSet executeQuery (String query){

        try {
            Connection connection = DBConnector.connect();
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Method that executes an SQL query and DOESN'T return a ResultSet
    private static void executeUpdate(String query){

        try {
            Connection connection = DBConnector.connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //----------------------------------------------------------

    // Method that shows all records in the "users" table
    public static void showUsers(){

        try{
            ResultSet resultSet = executeQuery("SELECT * FROM users");
            while(resultSet.next()) {
                System.out.print(
                        resultSet.getInt("id") + " " +
                                resultSet.getString("username") + " " +
                                resultSet.getInt("age") + " " +
                                resultSet.getString("city") + " " +
                                resultSet.getString("timestamp"));
                System.out.println();
            }
        }catch(SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Method that adds a new record to the "users" table
    public static void addUser(String username, int age, String city){

        try{
            Connection connection = DBConnector.connect();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO users (username, age, city) VALUES (?, ?, ?)");
            pstm.setString(1, username );
            pstm.setInt(2, age);
            pstm.setString(3, city);
            pstm.executeUpdate();
        }catch(SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Method that removes a record of a specific "id" from the "users" table
    public static void deleteUser() throws SQLException {

        System.out.println("Którego użytkownika usunąć? Podaj jego id:");
        int id = 0;
        Scanner scan = new Scanner(System.in);
        id = scan.nextInt();
        String query = "DELETE FROM users WHERE id=" + Integer.toString(id);
        executeUpdate(query);
    }

    // Method that modifies a record of a specific "id" from the "users" table
    public static void modifyUser(){

        System.out.println("Którego użytkownika zmodyfikować? Podaj jego id:");
        int id = 0;
        Scanner scan = new Scanner(System.in);
        id = scan.nextInt();
        System.out.println("Podaj jego nową nazwę:");
        String username = "";
        username = scan.next();
        System.out.println("Podaj jego nowy wiek:");
        int age = 0;
        age = scan.nextInt();
        System.out.println("Podaj jego nowe miasto:");
        String city = "www";
        city = scan.next();

        String query = "UPDATE users SET username='" + username + "', age="+ Integer.toString(id) + ", city='" + city +"' WHERE id=" + Integer.toString(id);
        executeUpdate(query);
    }
}
