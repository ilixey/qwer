package com.example.demo1.servlets.commands.impl.activity;

import com.example.demo1.entities.Activity;
import com.example.demo1.services.dbActivityService;
import com.example.demo1.servlets.commands.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowActivities implements Command {

    /**
     * Команда перенаправляет пользователя на страницу activity-list.jsp
     * Передаёт listActivity состоящий из LinkedList<Activity>
     * При попадании в эту команду, ссылка должна иметь URL переменную - USER_ID
     * USER_ID используется в методе services.dbActivityService.getActivities(long USER_ID)
     * getActivities() создаёт выборку деятельности для определённого человека по его USER_ID
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Activity> activityList = dbActivityService.getInstance().getActivities(Long.parseLong(request.getParameter("userId")));
            request.setAttribute("listActivity", activityList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("activity-list.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
