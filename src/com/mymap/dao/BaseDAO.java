package com.mymap.dao;

import java.sql.*;

/**
 * Created by DCH on 2018/4/22.
 */
public class BaseDAO {

    private final static String driver = "com.mysql.jdbc.Driver";

    private final static String url = "jdbc:mysql://localhost:3306/mymap?useUnicode=true&characterEncoding=utf8";

    private final static String name = "root";

    private final static String password = "root";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("注册驱动程序失败！");
            throw new RuntimeException(e);
        }
    }

    public Connection getCon() {
        try {
            Connection conn = DriverManager.getConnection(url, name, password);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }
}
