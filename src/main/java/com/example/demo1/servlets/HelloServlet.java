package com.example.demo1.servlets;

import com.example.demo1.entities.User;
import com.example.demo1.services.dbService;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    private com.example.demo1.services.dbService dbService = new dbService();

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        List<User> userList;
        try {
            userList = dbService.getUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = response.getWriter();
        for(User user:userList){
            out.println(user.getSurname() + "     " + user.getSurname() + "    " + user.getAge());
        }

    }

    public void destroy() {
    }
}