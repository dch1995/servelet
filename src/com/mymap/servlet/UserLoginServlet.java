package com.mymap.servlet;

import com.mymap.dao.UserDAO;
import com.mymap.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by DCH on 2018/4/23.
 */
@WebServlet(name = "UserLoginServlet", urlPatterns = "/servlet/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    private User user = new User();
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("text/html,charset=utf-8");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String password = request.getParameter("password");
        try {
            user = userDAO.selectUser(phone, password);
            if (user != null) {
                request.setAttribute("success", 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
