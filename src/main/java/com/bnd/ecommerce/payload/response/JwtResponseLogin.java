package com.bnd.ecommerce.payload.response;

import com.bnd.ecommerce.entity.Role;

import java.util.List;

public class JwtResponseLogin {
    private String token;
    private static final String TYPE = "Bearer";

    private int userId;
    private String email;
    private List<Role> listRoles;

    public JwtResponseLogin(String token, int userId, String email) {
        this.token = token;
        this.userId = userId;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
