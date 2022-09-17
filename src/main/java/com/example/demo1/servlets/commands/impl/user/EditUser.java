package com.example.demo1.servlets.commands.impl.user;

import com.example.demo1.entities.User;
import com.example.demo1.services.dbUserService;
import com.example.demo1.servlets.commands.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUser implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        User user = dbUserService.getInstance().selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", user);
        dispatcher.forward(request, response);
    }
}
