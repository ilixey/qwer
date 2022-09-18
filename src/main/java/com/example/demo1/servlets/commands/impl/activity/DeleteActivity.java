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
            long id = Long.parseLong(request.getParameter("id"));
            dbActivityService.getInstance().deleteActivity(id);
            response.sendRedirect("activity-list");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}