package com.example.demo1.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbService {
    private static final String url = "jdbc:postgresql://34.116.188.24:5432/testdb";
    private static final String login = "postgres";
    private static final String password = "postgres";

    private static final Connection connection;

    static {
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
        }
        catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
