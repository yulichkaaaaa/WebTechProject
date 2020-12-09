package com.yuliana.controller;

import com.yuliana.beans.Product;
import com.yuliana.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class CatalogController extends HttpServlet {

    private static final ProductDao productDao = new ProductDao();
    private static final int ITEMS_COUNT = 16;
    private static final String NAME_ATTRIBUTE = "productName";
    private static final String PRICE_ATTRIBUTE = "productPrice";
    private static final String PICTURE_ATTRIBUTE = "productPicture";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("categories");
        String inStock = request.getParameter("stock");
        Set<Product> productsInStock = productDao.getAllProducts();
        Set<Product> productsByCategory;
        if(inStock != null){
             productsInStock = productDao.findProductsInStock();
        }
        if(!category.equals("choose a category")){
            productsByCategory = productDao.findProductsByCategory(category);
        }else{
            productsByCategory =productDao.getAllProducts();
        }
        productsByCategory.retainAll(productsInStock);
        request.setAttribute("products", productsByCategory);
        request.getRequestDispatcher("/catalog.jsp").forward(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        Set<Product> products = productDao.getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/catalog.jsp").forward(request, response);
    }
}
