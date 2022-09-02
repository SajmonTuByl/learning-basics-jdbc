package org.example;


import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        DBOperations.showUsers();

        DBOperations.addUser("dsdsdsd", 34, "London");

        DBOperations.showUsers();

        DBOperations.modifyUser();

        DBOperations.showUsers();

    }
}