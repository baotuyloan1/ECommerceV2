package com.bnd.ecommerce.dto.api;
/**
 * @author BAO 6/29/2023
 */
public class CustomerDtoLogin {

    private String userName;

    private String password;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
