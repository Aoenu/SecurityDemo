package com.sec.oauth2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baoben.wu@hand-china.com
 * @Date 2018/4/23.
 * @description
 */
@RestController
public class OauthController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity sayHello() {
//        debug 查看权限信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return ResponseEntity.ok("Hello 游客!");
        }
        return ResponseEntity.ok(authentication);
    }

    @RequestMapping(value = "/secure", method = RequestMethod.GET)
    public ResponseEntity secure() {
        // debug 查看权限信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return "Secure Hello!";
        return ResponseEntity.ok(authentication);
    }
}
