package com.example.demo1.services;

import com.example.demo1.entities.Activity;
import com.example.demo1.util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;

public class dbActivityService {

    private final static dbActivityService INSTANCE = new dbActivityService();

    private static final String INSERT_ACTIVITY = "INSERT INTO activities (user_id, activity, duration, publication_date) VALUES (?, ?, ?, ?);";
    private static final String SELECT_ACTIVITY = "select activities.id, users.name, activities.activity, activities.duration, activities.publication_date from activities LEFT JOIN users ON activities.user_id = users.id where users.id =? ORDER BY activities.publication_date;";
    private static final String DELETE_ACTIVITY = "delete from activities where id=?;";
    private static final String UPDATE_ACTIVITY = "update activities set activity=?, duration=? WHERE id=?;";

    private dbActivityService() {
    }

    public static dbActivityService getInstance() {
        return INSTANCE;
    }

    public void addActivity(Activity activity) throws SQLException {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACTIVITY)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement.setLong(1, activity.getUser_id());
            preparedStatement.setString(2, activity.getActivity());
            preparedStatement.setBigDecimal(3, activity.getDuration());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.getErrorCode();
        }
    }

    public void deleteActivity(long id) throws SQLException {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACTIVITY)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public Activity selectActivity(long user_id) {
        Activity activity = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACTIVITY);) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement.setLong(1, user_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String doneActivity = rs.getString("activity");
                BigDecimal duration = rs.getBigDecimal("duration");
                Timestamp publicationDate = rs.getTimestamp("publication_date");
                activity = new Activity(id, doneActivity, duration, publicationDate);
            }
        } catch (SQLException e) {
            e.getErrorCode();
        }
        return activity;
    }

    public void updateActivity(Activity activity) throws SQLException {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ACTIVITY);) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            statement.setString(1, activity.getActivity());
            statement.setBigDecimal(2, activity.getDuration());
            statement.executeUpdate();
        }
    }
}
