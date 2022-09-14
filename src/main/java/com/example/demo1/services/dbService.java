package com.example.demo1.services;

import com.example.demo1.entities.User;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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

    public void addUser(User user) throws SQLException {
        String request = "INSERT INTO testdb (name, surname, age) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setInt(3, user.getAge());
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void deleteUser(long id) throws SQLException {
        String request = "DELETE FROM testdb WHERE id=" + id;
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public List<User> getUsers() throws SQLException{
        String request = "SELECT * FROM users";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> userList = new LinkedList<>();
        while (resultSet.next()){
            User user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
            userList.add(user);
        }
        return userList;
    }


}
