package com.yuliana.dao;

import com.yuliana.beans.CartItem;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CartDao {

    private final ConnectionPool pool = ConnectionPool.getInstance();
    private final Connection connection = pool.getConnection();
    private static final String ADD_TO_CART = "INSERT INTO cart_items (product_id , user_id, count) VALUES ( ? , ?, ?)";
    private static final String CHANGE_COUNT = "UPDATE cart_items SET count = count + 1 WHERE product_id = ?";
    private static final String ITEM_IN_CART = "SELECT * FROM cart_items WHERE product_id = ? AND user_id = ?";
    private static final String PRODUCT_INFO = "SELECT p.title AS p_title, p.price AS p_price, p.count AS p_count,  " +
                                                "c.count FROM products p " +
                                                "INNER JOIN cart_items c ON p.product_id = c.product_id " +
                                                "WHERE c.product_id = ?";
    private static final String CART_ITEMS = "SELECT p.title AS p_title, p.price AS p_price, p.count AS p_count, " +
                                            "c.count FROM products p " +
                                            "INNER JOIN cart_items c ON p.product_id = c.product_id " +
                                            "WHERE c.user_id = ?";
    private static final String CLEAN_CART = "DELETE FROM cart_items WHERE user_id = ?";
    private final Logger logger;

    public CartDao(){
        logger = Logger.getLogger(this.getClass());
        PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("log4j.properties"));
    }

    public Set<CartItem> getCartItems(int userId){
        Set<CartItem> cartItems = new HashSet<>();
        try(PreparedStatement statement = connection.prepareStatement(CART_ITEMS)){
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                cartItems.add(fillItem(result));
            }
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return cartItems;
    }

    public void addToCart(int productId, int userId){
        if(isInCart(productId, userId)){
            try(PreparedStatement statement = connection.prepareStatement(CHANGE_COUNT)){
                statement.setInt(1, productId);
                statement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else{
            try(PreparedStatement statement = connection.prepareStatement(ADD_TO_CART)){
                statement.setInt(1, productId);
                statement.setInt(2, userId);
                statement.setInt(3, 1);
                statement.executeUpdate();
            }catch (SQLException e){
                logger.error(e.getMessage());
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
            logger.error(e.getMessage());
        }
        return false;
    }

    public CartItem getProductInfo(int productId){
        CartItem cartItem = null;
        try(PreparedStatement statement = connection.prepareStatement(PRODUCT_INFO)){
            statement.setInt(1, productId);
            ResultSet result = statement.executeQuery();
            cartItem = fillItem(result);
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return cartItem;
    }

    public void cleanCart(int userId){
        try(PreparedStatement statement = connection.prepareStatement(CLEAN_CART)){
            statement.setInt(1, userId);
            statement.executeUpdate();
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
    }

    public CartItem fillItem(ResultSet result){
        CartItem cartItem= null;
        try {
            cartItem = new CartItem();
            cartItem.setTitle(result.getString("p_title"));
            cartItem.setPrice(result.getFloat("p_price"));
            cartItem.setCount(result.getInt("count"));
        }catch (SQLException e){
            logger.error(e.getMessage());
        }
        return cartItem;
    }

}
