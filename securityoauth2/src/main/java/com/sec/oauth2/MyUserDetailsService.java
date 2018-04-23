package com.sec.oauth2;

import com.sec.oauth2.dto.User;
import com.sec.oauth2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author baoben.wu@hand-china.com
 * @Date 2018/4/4.
 * @description 自定义UserDetailsService
 */
@Component("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        username = username.toLowerCase();
        User loginUser = userMapper.getUserByUsername(username);
        if (loginUser == null) {
            throw new NameNotFundException("User " + username + " was not found in the database");
        }
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String authority : userMapper.getUserAuthority(loginUser.getUsername())) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);
            grantedAuthorities.add(grantedAuthority);
        }
        return new org.springframework.security.core.userdetails.User(loginUser.getUsername(), loginUser.getPassword(), grantedAuthorities);
    }
}
