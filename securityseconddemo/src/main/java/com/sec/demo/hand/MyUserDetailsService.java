package com.sec.demo.hand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author baoben.wu@hand-china.com
 * @Date 2018/3/28.
 * @description
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    final static List<LoginUser> loginAuthorityList = new ArrayList<LoginUser>();

    //维护一个ip白名单列表，每个ip对应一定的权限
    static {
        loginAuthorityList.add(new LoginUser("admin", "admin", "ROLE_ADMIN"));
        loginAuthorityList.add(new LoginUser("test", "test", "ROLE_TEST"));
        loginAuthorityList.add(new LoginUser("user", "user", "ROLE_USER"));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser userEntity;
        if ("admin".equals(username)) {
            userEntity = loginAuthorityList.get(0);
        } else if ("test".equals(username)) {
            userEntity = loginAuthorityList.get(1);
        } else if ("user".equals(username)) {
            userEntity = loginAuthorityList.get(2);
        } else {
            userEntity = null;
        }
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = createAuthorities(userEntity.getRoles());
        return new User(userEntity.getUsername(), userEntity.getPassword(), simpleGrantedAuthorities);
//        return new User("admin",passwordEncoder.encode("123456"), createAuthorities("ROLE_USER"));
    }

    /**
     * 权限字符串转化
     * <p>
     * 如 "USER,ADMIN" -> SimpleGrantedAuthority("USER") + SimpleGrantedAuthority("ADMIN")
     *
     * @param roleStr 权限字符串
     */
    private List<SimpleGrantedAuthority> createAuthorities(String roleStr) {
        String[] roles = roleStr.split(",");
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for (String role : roles) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return simpleGrantedAuthorities;
    }

}
