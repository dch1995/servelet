package com.mymap.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by DCH on 2018/4/22.
 */
public class User implements Serializable {

    public User(){

    }

    public User(String userName,String password,BigInteger phone,String code){
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.code = code;
    }

    public User(int id,String userName,String password,BigInteger phone,String code){
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.code = code;
    }

    private int id;

    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigInteger getPhone() {
        return phone;
    }

    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private BigInteger phone;

    private String password;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;


}
