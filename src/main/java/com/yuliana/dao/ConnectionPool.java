package com.yuliana.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPool {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/javaweb?serverTimezone=Europe/Minsk&allowMultiQueries=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "yuliana245yuliana";
    private static ConnectionPool instance;

    private ConnectionPool(){ }

    public static ConnectionPool getInstance() {
        if(instance==null) {
            instance = new ConnectionPool();
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
