package com.sec.oauth2.dto;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author baoben.wu@hand-china.com
 * @Date 2018/4/4.
 * @description
 */
@Table(name = "user")
public class User {
    @Id
    @Size(min = 0, max = 50)
    @Column
    private String username;

    @Column
    @Size(min = 0, max = 500)
    private String password;

    @Column
    @Size(min = 0, max = 50)
    private String email;

    @Column
    private boolean activated;

    @Size(min = 0, max = 100)
    @Column(name = "activationkey")
    private String activationKey;

    @Size(min = 0, max = 100)
    @Column(name = "resetpasswordkey")
    private String resetPasswordKey;

    @Transient
    private List<String> userAuthorities;

    public List<String> getUserAuthorities() {
        return userAuthorities;
    }

    public User setUserAuthorities(List<String> userAuthorities) {
        this.userAuthorities = userAuthorities;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isActivated() {
        return activated;
    }

    public User setActivated(boolean activated) {
        this.activated = activated;
        return this;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public User setActivationKey(String activationKey) {
        this.activationKey = activationKey;
        return this;
    }

    public String getResetPasswordKey() {
        return resetPasswordKey;
    }

    public User setResetPasswordKey(String resetPasswordKey) {
        this.resetPasswordKey = resetPasswordKey;
        return this;
    }
}
