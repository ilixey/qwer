package com.example.demo1.servlets.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}

