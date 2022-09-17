package com.example.demo1.servlets.commands.impl.activity;

import com.example.demo1.entities.Activity;
import com.example.demo1.services.dbActivityService;
import com.example.demo1.servlets.commands.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditActivity implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Activity activity = dbActivityService.getInstance().selectActivity(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("activity-form.jsp");
        request.setAttribute("activity", activity);
        dispatcher.forward(request, response);
    }
}
