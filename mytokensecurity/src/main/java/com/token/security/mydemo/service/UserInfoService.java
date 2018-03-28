package com.token.security.mydemo.service;

import com.token.security.mydemo.pojo.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Levin
 * @date 2017-08-15.
 */
@Service
public class UserInfoService {

    public UserInfo findByName(String username) {
        //TODO 该处只是为了模拟查询数据库
        if (username.equals("member")) {
            return new UserInfo("member", "member","USER");
        }
        return new UserInfo("test", "test","ADMIN");
    }
}
