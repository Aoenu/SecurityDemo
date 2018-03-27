package com.sec.demo.hand;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author baoben.wu@hand-china.com
 * @Date 2018/3/27.
 * @description
 */
public class LoginAuthenticationProvider implements AuthenticationProvider {

    final static Map<LoginUser, SimpleGrantedAuthority> loginAuthorityMap = new ConcurrentHashMap<LoginUser, SimpleGrantedAuthority>();

    //维护一个ip白名单列表，每个ip对应一定的权限
    static {
        loginAuthorityMap.put(new LoginUser("admin", "admin"), new SimpleGrantedAuthority("ADMIN"));
        loginAuthorityMap.put(new LoginUser("user", "user"), new SimpleGrantedAuthority("USER"));
        loginAuthorityMap.put(new LoginUser("test", "test"), new SimpleGrantedAuthority("TEST"));
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LoginAuthenticationToken loginAuthenticationToken = (LoginAuthenticationToken) authentication;
        String username = loginAuthenticationToken.getUsername();
        String password = loginAuthenticationToken.getPassword();
        SimpleGrantedAuthority simpleGrantedAuthority = loginAuthorityMap.get(new LoginUser(username, password));
        //不在白名单列表中
        if (simpleGrantedAuthority == null) {
            return null;
        } else {
            //封装权限信息，并且此时身份已经被认证 see
            return new LoginAuthenticationToken(username, password, Arrays.asList(simpleGrantedAuthority));
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (LoginAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
