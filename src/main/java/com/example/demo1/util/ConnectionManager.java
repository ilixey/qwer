package com.example.demo1.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String URL = "jdbc:postgresql://34.116.188.24:5432/vaspiakou";
    private static final String LOGIN = "vaspiakou";
    private static final String PASSWORD = "vaspiakou";

    private ConnectionManager() {
    }

    public static Connection get() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
