package com.example.demo1.servlets.commands.impl.activity;

import com.example.demo1.entities.Activity;
import com.example.demo1.services.dbActivityService;
import com.example.demo1.servlets.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class InsertActivity implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long user_id = Long.valueOf(request.getParameter("user_id"));
            String doneActivity = request.getParameter("activity");
            BigDecimal duration = BigDecimal.valueOf(Double.parseDouble(request.getParameter("duration")));
            Activity activity = new Activity(doneActivity,user_id, duration);
            dbActivityService.getInstance().addActivity(activity);

            Long userId = Long.valueOf(request.getParameter("userId"));
            String userName = request.getParameter("name");
            String urlPrefix = request.getContextPath();
            response.sendRedirect(urlPrefix + "/activity-list?userId=" + userId + "&name=" + userName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
