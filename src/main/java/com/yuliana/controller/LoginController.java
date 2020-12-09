package com.yuliana.controller;

import com.yuliana.beans.User;
import com.yuliana.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        UserDao dao = new UserDao();
        if(dao.authorizeUser(user)){
            HttpSession session = request.getSession();
            session.setAttribute("status","online");
            session.setAttribute("userId", user.getUserId());
            response.sendRedirect("catalog");
        }else{
            request.setAttribute("error", "error, please try again!!!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
