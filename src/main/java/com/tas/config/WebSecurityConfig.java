package com.tas.config;

import com.tas.service.UserEntityDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserEntityDetailService userEntityDetailService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userEntityDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests().antMatchers( "/login", "/logout","/resisger","/resisger/data").permitAll().anyRequest();
        http.authorizeRequests().antMatchers("/manager-tranning/projects/**","/home","/dist/**","/plugins/**").access("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MANAGER')").antMatchers("/category/**","/manager-tranning/project/**","/manager-tranning/device/**")
                .access("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')")
                .antMatchers("/**").access("hasAnyRole('ROLE_ADMIN')");



        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/home");
        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?message=error")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?message=logout");

    }

}
