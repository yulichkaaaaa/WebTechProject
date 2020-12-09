package com.yuliana.controller;

import com.yuliana.dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartController extends HttpServlet {

    private final CartDao cartDao = new CartDao();
    private static final String regex = "\\d+$";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        String path = request.getRequestURL().toString();
        cartDao.addToCart(findProductId(path), userId);
        request.setAttribute("cartItems", cartDao.getCartItems(userId));
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    private int findProductId(String path){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(path);
        int productId = 0;
        if(matcher.find()) {
            productId = Integer.parseInt(matcher.group());
        }
        return productId;
    }

}
