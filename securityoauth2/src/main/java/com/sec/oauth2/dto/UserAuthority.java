package com.sec.oauth2.dto;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author baoben.wu@hand-china.com
 * @Date 2018/4/4.
 * @description
 */
@Table(name = "user_authority")
public class UserAuthority {
    @Id
    @NotNull
    @Size(min = 0, max = 50)
    private String username;

    @NotNull
    @Size(min = 0, max = 50)
    private String authority;

    public String getUsername() {
        return username;
    }

    public UserAuthority setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getAuthority() {
        return authority;
    }

    public UserAuthority setAuthority(String authority) {
        this.authority = authority;
        return this;
    }
}
