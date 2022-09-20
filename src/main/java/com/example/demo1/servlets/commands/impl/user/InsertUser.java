package com.example.demo1.servlets.commands.impl.user;

import com.example.demo1.entities.User;
import com.example.demo1.services.dbUserService;
import com.example.demo1.servlets.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class InsertUser implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            int age = Integer.parseInt(request.getParameter("age"));

            User user = new User(name, surname, age);
            dbUserService.getInstance().addUser(user);
            String urlPrefix = request.getContextPath();
            response.sendRedirect(urlPrefix + "/users-list");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
