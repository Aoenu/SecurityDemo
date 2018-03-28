package com.sec.demo.hand;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

/**
 * @author baoben.wu@hand-china.com
 * @Date 2018/3/27.
 * @description
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //ip认证者配置
    @Bean
    LoginAuthenticationProvider loginAuthenticationProvider() {
        return new LoginAuthenticationProvider();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "home").permitAll()
                .antMatchers("Login").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("Login")
                .authenticationEntryPoint(loginUrlAuthenticationEntryPoint());
        http.addFilterBefore(loginAuthenticationProcessingFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    LoginAuthenticationProcessingFilter loginAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        LoginAuthenticationProcessingFilter loginAuthenticationProcessingFilter = new LoginAuthenticationProcessingFilter("Login");
        loginAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager);
        loginAuthenticationProcessingFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/Login?error"));
        return loginAuthenticationProcessingFilter;
    }


    //配置登录端点
    @Bean
    LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
        LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint
                ("/Login");
        return loginUrlAuthenticationEntryPoint;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(loginAuthenticationProvider());
    }
}
