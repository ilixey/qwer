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


// TODO : А если заменить WebServlet на /users (+ изменить соответстсующие ссылки в user-list,user-form).
//  Мб из-за маппинга "/" ActivitiServlet теряет приоритет в поиске нужных команд?


@WebServlet("/")
public class UserServlet extends HttpServlet {

    Map<String, Command> actionMap;

    public void init() {
        actionMap = new HashMap<String, Command>();
        // ADD NEW : To create new record in Users table. Following action - INSERT
        actionMap.put("/new-user", new AddNewUser());
        // INSERT : To insert into table new user
        actionMap.put("/insert-user", new InsertUser());
        // DELETE : To delete record in table about selected user
        actionMap.put("/delete-user", new DeleteUser());
        // EDIT : To start modifying an existing user. Following action - UPDATE
        actionMap.put("/edit-user", new EditUser());
        // UPDATE : To update information about user during editing existing user
        actionMap.put("/update-user", new UpdateUser());
        // SHOW ALL : To show all existing users. A.K.A. HomePage
        actionMap.put("/users-list", new ShowAllUsers());
        // ACTIVITIES : To redirect onto url /activities and set active Servlet to ActivityServlet
        actionMap.put("/activities", new RedirectToActivities());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // Reading requested action
        String action = request.getServletPath();
        // Searching for a suitable action by actionMap. If action not found - /users-list
        Command command = actionMap.getOrDefault(action, actionMap.get("/users-list"));
        command.execute(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
