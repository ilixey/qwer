package com.example.demo1.servlets.commands.impl.activity;

import com.example.demo1.entities.Activity;
import com.example.demo1.entities.User;
import com.example.demo1.services.dbActivityService;
import com.example.demo1.services.dbUserService;
import com.example.demo1.servlets.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class UpdateActivity implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            String doneActivity = request.getParameter("activity");
            BigDecimal duration = BigDecimal.valueOf(Long.parseLong(request.getParameter("duration")));

            Activity activity = new Activity(id, doneActivity, duration);
            dbActivityService.getInstance().updateActivity(activity);
            response.sendRedirect("list");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
