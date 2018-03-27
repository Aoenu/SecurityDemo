package com.sec.demo.hand;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author baoben.wu@hand-china.com
 * @Date 2018/3/27.
 * @description
 */
public class LoginAuthenticationToken extends AbstractAuthenticationToken {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public LoginAuthenticationToken setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginAuthenticationToken setPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginAuthenticationToken(String username,String password, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = username;
        this.password = password;
        super.setAuthenticated(true);
    }

    public LoginAuthenticationToken(String username) {
        super(null);
        this.username = username;
        super.setAuthenticated(false);
    }


    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }
}
