package com.mymap.dao;

import com.mymap.entity.User;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理用户登陆注册
 * Created by DCH on 2018/4/22.
 */
public class UserDAO {

    private BaseDAO baseDAO = new BaseDAO();

    Connection conn = null;

    PreparedStatement statement = null;

    ResultSet resultSet = null;

    boolean onSuccess = false;

    int isLogin = 0;

    //用户注册
    public boolean insertUser(User user) throws SQLException {

        String sql = "insert into user (name,phone,password,code) values (?,?,?,?)";

        this.conn = baseDAO.getCon();

        statement = conn.prepareStatement(sql);

        statement.setString(1, user.getUserName());

        statement.setObject(2,user.getPhone());

        statement.setString(3, user.getPassword());

        statement.setString(4,user.getCode());

        int a = statement.executeUpdate();

        if (a > 0) {
            onSuccess = true;
        }

        baseDAO.close(conn, statement, resultSet);

        return onSuccess;
    }

    //手机号是否被注册
    public int selectUserByphone(BigInteger phone) throws SQLException {

        String sql = "select * from user where phone = ?";

        conn = baseDAO.getCon();

        statement = conn.prepareStatement(sql);

        statement.setObject(1, phone);

        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            isLogin = 1;
        }

        baseDAO.close(conn, statement, resultSet);

        return isLogin;
    }

    //用户登陆
    public User selectUser(int phone, String password) throws SQLException {

        User user = new User();

        String sql = "select * from user where phone = ?";

        conn = baseDAO.getCon();

        statement = conn.prepareStatement(sql);

        statement.setInt(1, phone);

        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String pass = resultSet.getString("password");
            String name = resultSet.getString("name");
            user.setPassword(pass);
            user.setUserName(name);
            if (password != null && pass.equals(password) && "".equals(password)) {
                return user;
            } else {
                return null;
            }
        }
        baseDAO.close(conn, statement, resultSet);
        return user;
    }
}
