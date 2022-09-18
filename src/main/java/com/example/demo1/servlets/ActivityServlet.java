package com.example.demo1.servlets;

import com.example.demo1.servlets.commands.Command;
import com.example.demo1.servlets.commands.impl.activity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/activities")
public class ActivityServlet extends HttpServlet {

    Map<String, Command> actionMap;

    public void init() {
        actionMap = new HashMap<String, Command>();
        // LIST : возвращает список деятельности для пользователя при условии что в ссылке есть URL переменная
        actionMap.put("/activity-list", new ShowActivities());
        // NEW : перенаправляет на activity-form.jsp для добавления новой активности. Следующее действие - INSERT
        actionMap.put("/new-activity", new AddNewActivity());
        // INSERT : собирает введённую информацию со страницы activity-form.jsp и создаёт запись в таблице
        actionMap.put("/insert-activity", new InsertActivity());
        // DELETE : удаляет запись о деятельности
        actionMap.put("/delete-activity", new DeleteActivity());
        // EDIT : перенаправляет на activity-form.jsp для изменения существующей активности. Следующее действие - UPDATE
        actionMap.put("/edit-activity", new EditActivity());
        // UPDATE : собирает введённую информацию со страницы activity-form.jsp и обновляет запись в таблице
        actionMap.put("/update-activity", new UpdateActivity());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String action = request.getServletPath();
        Command command = actionMap.getOrDefault(action, actionMap.get("/activity-list"));
        command.execute(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
