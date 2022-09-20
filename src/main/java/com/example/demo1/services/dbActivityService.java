package com.example.demo1.services;

import com.example.demo1.entities.Activity;
import com.example.demo1.entities.User;
import com.example.demo1.util.ConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class dbActivityService {

    private final static dbActivityService INSTANCE = new dbActivityService();

    private static final String INSERT_ACTIVITY = "INSERT INTO activities (user_id, activity, duration, publication_date, deletable) VALUES (?, ?, ?, ?, true);";
    private static final String SELECT_ACTIVITY = "SELECT * FROM activities;";
    private static final String SELECT_ALL_ACTIVITIES_BY_ID = "SELECT * FROM activities WHERE user_id = ?;";
    private static final String DELETE_ACTIVITY = "DELETE FROM activities WHERE id=?;";
    private static final String UPDATE_ACTIVITY = "UPDATE activities SET activity=?, duration=? WHERE id=?;";

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

    public List<Activity> getActivities(long user_id) throws SQLException {
        List<Activity> activityList = new LinkedList<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACTIVITIES_BY_ID)) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            preparedStatement.setLong(1,user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Activity activity = new Activity(resultSet.getLong(1), resultSet.getLong(2), resultSet.getString(3), resultSet.getBigDecimal(4), resultSet.getTimestamp(5));
                activityList.add(activity);
            }
            System.out.println(activityList);
            return activityList;
        }
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
