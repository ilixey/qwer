package com.example.demo1.servlets.commands.impl.activity;

import com.example.demo1.services.dbActivityService;
import com.example.demo1.servlets.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteActivity implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long activity_id = Long.parseLong(request.getParameter("activityId"));
            dbActivityService.getInstance().deleteActivity(activity_id);

            Long userId = Long.parseLong(request.getParameter("userId"));
            String userName = request.getParameter("name");
            String urlPrefix = request.getContextPath();

            response.sendRedirect(urlPrefix + "/activity-list?userId=" + userId + "&name=" + userName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
