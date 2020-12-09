package com.yuliana.dao;

import com.yuliana.beans.Product;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ProductDao {

    private final ConnectionPool pool = new ConnectionPool();
    private final Connection connection = pool.getConnection();
    private static final String CATALOG_ITEMS = "SELECT * FROM products";
    private static final String ITEMS_BY_CATEGORY = "SELECT * FROM products WHERE category = ?";
    private static final String ITEMS_IN_STOCK = "SELECT * FROM products WHERE count > 0";

    public Set<Product> getAllProducts(){
        Set<Product> products = new HashSet<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(CATALOG_ITEMS);
            while (result.next()){
                products.add(fillProduct(result));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    public Set<Product> findProductsByCategory(String category){
        Set<Product> products = new HashSet<>();
        try (PreparedStatement statement = connection.prepareStatement(ITEMS_BY_CATEGORY)){
            statement.setString(1, category);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                if(result.getString(6).equals(category)){
                    products.add(fillProduct(result));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    public Set<Product> findProductsInStock(){
        Set<Product> products = new HashSet<>();
        try (Statement statement = connection.createStatement()){
            ResultSet result = statement.executeQuery(ITEMS_IN_STOCK);
            while (result.next()) {
                products.add(fillProduct(result));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    private Product fillProduct(ResultSet result){
        Product product = null;
        try {
            product = new Product();
            product.setProductId(result.getInt(1));
            product.setTitle(result.getString(2));
            product.setCount(result.getInt(3));
            product.setPictureName(result.getString(4));
            product.setPrice(result.getFloat(5));
            product.setCategory(result.getString(6));
            product.setProductId(result.getInt(1));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }
}
