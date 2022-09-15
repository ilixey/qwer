package com.example.demo1.services;

import com.example.demo1.entities.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class dbService {
    private static final String url = "jdbc:postgresql://34.116.188.24:5432/testdb";
    private static final String login = "postgres";
    private static final String password = "postgres";

    private static final String INSERT_USER = "INSERT INTO users (name, surname, age) VALUES (?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "select * from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USER = "delete from users where id=?;";
    private static final String UPDATE_USER = "update users set name=?, surname=?, age=? where id=?;";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public void addUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
           // connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(true);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getErrorCode();
        }
    }

    public void deleteUser(long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }

    }

    public List<User> getUsers() throws SQLException {
        List<User> userList = new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                userList.add(user);
            }
            System.out.println(userList);
            return userList;
        }
    }

    public User selectUser(long id) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setLong(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                user = new User(id, name, surname, age);
            }
        } catch (SQLException e) {
            e.getErrorCode();
        }
        return user;
    }

    public void updateUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getAge());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
        }
    }
}
