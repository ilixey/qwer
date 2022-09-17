package com.example.demo1.servlets;

import com.example.demo1.servlets.commands.Command;
import com.example.demo1.servlets.commands.impl.activity.AddNewActivity;
import com.example.demo1.servlets.commands.impl.activity.DeleteActivity;
import com.example.demo1.servlets.commands.impl.activity.EditActivity;
import com.example.demo1.servlets.commands.impl.activity.InsertActivity;
import com.example.demo1.servlets.commands.impl.user.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/activity")
public class ActivityServlet extends HttpServlet {

    Map<String, Command> actionMap;

    public void init() {
        actionMap = new HashMap<String, Command>();
        actionMap.put("/new-activity", new AddNewActivity());
        actionMap.put("/insert-activity", new InsertActivity());
        actionMap.put("/delete-activity", new DeleteActivity());
        actionMap.put("/edit-activity", new EditActivity());
        actionMap.put("/update-activity", new UpdateUser());
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
