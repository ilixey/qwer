package com.example.demo1.servlets;

import com.example.demo1.entities.User;
import com.example.demo1.services.dbService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    dbService dbService;

    public void init(){
        dbService = new dbService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action){
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                try {
                    insertUser(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/delete":
                try {
                    deleteUser(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                try {
                    updateUser(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                try {
                    listUser(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        //long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int age = Integer.parseInt(request.getParameter("age"));
        User user = new User(name, surname, age);
        dbService.addUser(user);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        dbService.deleteUser(id);
        response.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = Long.parseLong(request.getParameter("id"));
        User user = dbService.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", user);
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int age = Integer.parseInt(request.getParameter("age"));

        User user = new User(id, name, surname, age);
        dbService.updateUser(user);
        response.sendRedirect("list");
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<User> userList = dbService.getUsers();
        request.setAttribute("listUser", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }
}
