package com.yuliana.controller;

import com.yuliana.beans.CartItem;
import com.yuliana.dao.CartDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartController extends HttpServlet {

    private final CartDao cartDao = new CartDao();
    private static final String regex = "\\d+$";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        cartDao.cleanCart(userId);
        request.setAttribute("cartItems",new HashSet<CartItem>());
        request.setAttribute("amount", 0.0f);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        String path = request.getRequestURL().toString();
        cartDao.addToCart(findProductId(path), userId);
        Set<CartItem> items =  cartDao.getCartItems(userId);
        request.setAttribute("cartItems",items);
        cartSum(items, request);
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

    private void cartSum(Set<CartItem> cartItems, HttpServletRequest request){
        float sum = 0.0f;
        for(CartItem c : cartItems){
            sum += c.getPrice()*c.getCount();
        }
        request.setAttribute("amount", sum);
    }

}
