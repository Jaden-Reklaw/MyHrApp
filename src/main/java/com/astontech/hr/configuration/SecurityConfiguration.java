package com.astontech.hr.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    final static String AUTH_METHOD = "IN_MEMORY";

    //Quick way to create testing for auth
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        if(AUTH_METHOD.equals("IN_MEMORY")) {
            //Create some users, passwords and roles
            auth.inMemoryAuthentication().withUser("user").password("abc123").roles("USER");
            auth.inMemoryAuthentication().withUser("admin").password("abc123").roles("ADMIN");
            auth.inMemoryAuthentication().withUser("dba").password("abc123").roles("DBA");
        } else if(AUTH_METHOD.equals("LDAP")) {
            auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
        }
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        if(AUTH_METHOD.equals("IN_MEMORY")) {
            //region ACCESS CONTROL
            httpSecurity
                    //permit all with no authentication
                    //.authorizeRequests().antMatchers("/").permitAll()
                    //Create authentication for ADMIN and anything with URL=/admin/**
                    .authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                    .and()
                    //Create authentication for DBA and anything with URL=/console/**
                    .authorizeRequests().antMatchers("/console/**").access("hasRole('ROLE_DBA')");
            //Create authentication for USER
            //endregion

            //region LOGIN PAGE
            httpSecurity
                    //loginPage is the url to login
                    //loginProcessingUrl is the action on the login form in the jsp
                    .formLogin().loginPage("/login").loginProcessingUrl("/login.do")
                    //defaultSuccessUrl is where to go once login successful
                    //failureUrl goes back to login page but assgin err=1 to be used for erro
                    .defaultSuccessUrl("/").failureUrl("/login?err=1")
                    .usernameParameter("username").passwordParameter("password");
            //endregion
        } else if(AUTH_METHOD.equals("LDAP")) {
            httpSecurity
                    .authorizeRequests().antMatchers("/static/**").permitAll()
                    .and()
                    .authorizeRequests().antMatchers("/login**").permitAll()
                    .and()
                    .authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .and()
                    .logout()
                    .and()
                    .rememberMe();
        }


        //region ADVANCED SETTINGS
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
        //endregion
    }

    @Bean
    public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
        ActiveDirectoryLdapAuthenticationProvider authenticationProvider =
                new ActiveDirectoryLdapAuthenticationProvider("domain", "ldap://xxx.xxx.xxx.xxx:389");

        authenticationProvider.setConvertSubErrorCodesToExceptions(true);
        authenticationProvider.setUseAuthenticationRequestCredentials(true);

        return authenticationProvider;
    }
}
