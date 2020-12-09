package com.yuliana.dao;

import com.yuliana.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private final ConnectionPool pool = new ConnectionPool();
    private final Connection connection = pool.getConnection();
    private static final String REGISTER_USER = "INSERT INTO users ( name , email , password , role ) VALUES ( ? , ? , ? , ? )";
    private static final String LOGIN_USER = "SELECT * FROM users WHERE email = ? and password = ?;";

    public void insertUser(User user){
        try(PreparedStatement statement = connection.prepareStatement(REGISTER_USER)){
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, "user");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean authorizeUser(User user){
        try(PreparedStatement statement = connection.prepareStatement(LOGIN_USER)){
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            ResultSet result = statement.executeQuery();
            if(result.next()){
                user.setUserId(result.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
