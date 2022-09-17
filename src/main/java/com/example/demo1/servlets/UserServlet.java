package com.example.demo1.servlets;

import com.example.demo1.servlets.commands.Command;
import com.example.demo1.servlets.commands.impl.user.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class UserServlet extends HttpServlet {

    Map<String, Command> actionMap;

    public void init() {
        actionMap = new HashMap<String, Command>();
        actionMap.put("/new", new AddNewUser());
        actionMap.put("/insert", new InsertUser());
        actionMap.put("/delete", new DeleteUser());
        actionMap.put("/edit", new EditUser());
        actionMap.put("/update", new UpdateUser());
        actionMap.put("/list", new ShowAllUsers());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String action = request.getServletPath();
        Command command = actionMap.getOrDefault(action, actionMap.get("/list"));
        command.execute(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
