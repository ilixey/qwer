package com.example.demo1.servlets.commands.impl.activity;

import com.example.demo1.entities.Activity;
import com.example.demo1.services.dbActivityService;
import com.example.demo1.servlets.commands.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowActivities implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Аналог команды servlets.commands.impl.user.RedirectToActivities для activity команд.
         */

        try {
            List<Activity> activityList = dbActivityService.getInstance().getActivities(Long.parseLong(request.getParameter("id")));
            request.setAttribute("listActivity", activityList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("activity-list.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
