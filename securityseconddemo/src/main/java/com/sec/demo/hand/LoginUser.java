package com.sec.demo.hand;

/**
 * @author baoben.wu@hand-china.com
 * @Date 2018/3/27.
 * @description
 */
public class LoginUser {
    private String username;
    private String password;
    private String roles;

    public String getRoles() {
        return roles;
    }

    public LoginUser setRoles(String roles) {
        this.roles = roles;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoginUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginUser(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
