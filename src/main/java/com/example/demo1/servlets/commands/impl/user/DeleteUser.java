package com.example.demo1.servlets.commands.impl.user;

import com.example.demo1.services.dbUserService;
import com.example.demo1.servlets.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteUser implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            dbUserService.getInstance().deleteUser(id);
            response.sendRedirect("users-list");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
