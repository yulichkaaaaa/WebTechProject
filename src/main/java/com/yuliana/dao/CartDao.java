package com.yuliana.dao;

import com.yuliana.beans.Product;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class CartDao {

    private final ConnectionPool pool = new ConnectionPool();
    private final Connection connection = pool.getConnection();
    private static final String ADD_TO_CART = "INSERT INTO cart_items (product_id , user_id, count) VALUES ( ? , ?, ?)";
    private static final String CHANGE_COUNT = "UPDATE cart_items SET count = count + 1";
    private static final String ITEM_IN_CART = "SELECT * FROM cart_items WHERE product_id = ? AND user_id = ?";
    private static final String PRODUCT_INFO = "SELECT p.title AS p_title, p.price AS p_price, p.count AS p_count,  " +
                                                "p.pic_name AS p_pic_name, p.category AS p_category" +
                                                "FROM products p " +
                                                "INNER JOIN cart_items c ON p.product_id = c.product_id " +
                                                "WHERE c.product_id = ?";
    private static final String CART_ITEMS = "SELECT p.title AS p_title, p.price AS p_price, p.count AS p_count, " +
                                            "p.pic_name AS p_pic_name, p.category AS p_category " +
                                            "FROM products p " +
                                            "INNER JOIN cart_items c ON p.product_id = c.product_id " +
                                            "WHERE c.user_id = ?";

    public Set<Product> getCartItems(int userId){
        Set<Product> products = new HashSet<>();
        try(PreparedStatement statement = connection.prepareStatement(CART_ITEMS)){
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                products.add(fillProduct(result));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    public void addToCart(int productId, int userId){
        if(isInCart(productId, userId)){
            try(Statement statement = connection.createStatement()){
                statement.executeUpdate(CHANGE_COUNT);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else{
            try(PreparedStatement statement = connection.prepareStatement(ADD_TO_CART)){
                statement.setInt(1, productId);
                statement.setInt(2, productId);
                statement.setInt(3, 1);
                statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public boolean isInCart(int productId, int userId){
        try(PreparedStatement statement = connection.prepareStatement(ITEM_IN_CART)){
            statement.setInt(1, productId);
            statement.setInt(2, userId);
            ResultSet result = statement.executeQuery();
            return result.next();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Product getProductInfo(int productId){
        Product product = null;
        try(PreparedStatement statement = connection.prepareStatement(PRODUCT_INFO)){
            statement.setInt(1, productId);
            ResultSet result = statement.executeQuery();
            product = fillProduct(result);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }

    public static Product fillProduct(ResultSet result){
        Product product = null;
        try {
            product = new Product();
            product.setTitle(result.getString("p_title"));
            product.setCount(result.getInt("p_count"));
            product.setPictureName(result.getString("p_pic_name"));
            product.setPrice(result.getFloat("p_price"));
            product.setCategory(result.getString("p_category"));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }
}
