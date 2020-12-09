package com.yuliana.controller;

import com.yuliana.beans.User;
import com.yuliana.dao.UserDao;

import java.io.IOException;

public class RegisterController extends javax.servlet.http.HttpServlet {
    private User user;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        user = new User(name, email, password);

        UserDao dao = new UserDao();
        dao.insertUser(user);

        response.sendRedirect("catalog");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
