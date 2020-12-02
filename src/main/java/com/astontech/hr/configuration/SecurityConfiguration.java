package com.astontech.hr.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //Quick way to create testing for auth
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //Create some users, passwords and roles
        auth.inMemoryAuthentication().withUser("user").password("abc123").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("abc123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("abc123").roles("DBA");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                //permit all with no authentication
                //.authorizeRequests().antMatchers("/").permitAll()
                //Create authentication for ADMIN and anything with URL=/admin/**
                .authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .and()
                //Create authentication for DBA and anything with URL=/console/**
                .authorizeRequests().antMatchers("/console/**").access("hasRole('ROLE_DBA')");
                //Create authentication for USER

        //form login page
        httpSecurity.formLogin();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }
}
