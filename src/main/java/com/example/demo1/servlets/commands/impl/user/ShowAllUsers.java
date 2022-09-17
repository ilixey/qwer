package com.example.demo1.servlets.commands.impl.user;

import com.example.demo1.entities.User;
import com.example.demo1.services.dbUserService;
import com.example.demo1.servlets.commands.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowAllUsers implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> userList = dbUserService.getInstance().getUsers();
            request.setAttribute("listUser", userList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
