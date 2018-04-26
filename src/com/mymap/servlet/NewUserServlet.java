package com.mymap.servlet;

import com.mymap.dao.UserDAO;
import com.mymap.entity.User;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DCH on 2018/4/22.
 */
public class NewUserServlet extends javax.servlet.http.HttpServlet {
    private UserDAO userDAO = new UserDAO();
    private User user = new User();
    private Map<String, Object> resultMap = new HashMap<>();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        String phoneString = request.getParameter("phone");
        try {
            if (phoneString != null) {
                BigInteger phone = new BigInteger(phoneString);
                int isLogin = userDAO.selectUserByphone(phone);
                if (isLogin > 0) {
                    resultMap.put("key", "rephonenumber");
                    JSONObject jsonObject = JSONObject.fromObject(resultMap);
                    out.write(jsonObject.toString());
                } else {
                    user.setUserName(name);
                    user.setPassword(password);
                    user.setCode(code);
                    user.setPhone(phone);
                    boolean onSuccess = userDAO.insertUser(user);
                    if (onSuccess) {
                        resultMap.put("key", "onSuccess");
                        JSONObject jsonObject = JSONObject.fromObject(resultMap);
                        out.append(jsonObject.toString());
                    } else {
                        resultMap.put("key", "onFail");
                        JSONObject jsonObject = JSONObject.fromObject(resultMap);
                        out.append(jsonObject.toString());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        this.doPost(request, response);

    }
}
