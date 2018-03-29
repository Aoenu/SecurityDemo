package com.sec.demo.hand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author baoben.wu@hand-china.com
 * @Date 2018/3/27.
 * @description
 */
public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LoginAuthenticationToken loginAuthenticationToken = (LoginAuthenticationToken) authentication;
        String username = loginAuthenticationToken.getUsername();
        UserDetails user = this.retrieveUser(username, (LoginAuthenticationToken) authentication);
        if (user == null) {
            return null;
        }else {
            return this.createSuccessAuthentication(username,(LoginAuthenticationToken) authentication,user);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (LoginAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private UserDetails retrieveUser(String username, LoginAuthenticationToken authentication) throws AuthenticationException {
        UserDetails loadedUser = myUserDetailsService.loadUserByUsername(username);
        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        } else {
            return loadedUser;
        }
    }

    protected Authentication createSuccessAuthentication(String principal, LoginAuthenticationToken authentication, UserDetails user) {
        LoginAuthenticationToken result = new LoginAuthenticationToken(principal, authentication.getPassword(), user.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

}
