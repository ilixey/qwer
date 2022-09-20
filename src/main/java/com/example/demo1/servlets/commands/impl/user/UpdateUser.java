package com.example.demo1.servlets.commands.impl.user;

import com.example.demo1.entities.User;
import com.example.demo1.services.dbUserService;
import com.example.demo1.servlets.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateUser implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            int age = Integer.parseInt(request.getParameter("age"));

            User user = new User(id, name, surname, age);
            dbUserService.getInstance().updateUser(user);
            String urlPrefix = request.getContextPath();
            response.sendRedirect(urlPrefix + "/users-list");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
