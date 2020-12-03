package com.astontech.hr.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //final static String AUTH_METHOD = "LDAP";

    //link value from application properties using spring
    @Value("${spring.security.authentication.method}")
    private String authenticationMethod;

    @Value("${spring.security.ldap.domain}")
    private String ldapDomain;

    @Value("${spring.security.ldap.url}")
    private String ldapUrl;

    @Autowired
    private DataSource dataSource;

    //Quick way to create testing for auth
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        if(authenticationMethod.equals("NONE")) {

        }else if(authenticationMethod.equals("IN_MEMORY")) {
            //Create some users, passwords and roles
            auth.inMemoryAuthentication().withUser("user").password("abc123").roles("USER");
            auth.inMemoryAuthentication().withUser("admin").password("abc123").roles("ADMIN");
            auth.inMemoryAuthentication().withUser("dba").password("abc123").roles("DBA");
        } else if(authenticationMethod.equals("LDAP")) {
            auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
        } else if(authenticationMethod.equals("DATABASE")) {
            JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
            userDetailsService.setDataSource(dataSource);
            PasswordEncoder encoder = new BCryptPasswordEncoder();

            auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
            auth.jdbcAuthentication().dataSource(dataSource);

            if(!userDetailsService.userExists("user")) {
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                authorities.add(new SimpleGrantedAuthority("USER"));
                User userDetails = new User("user", encoder.encode("password"), authorities);

                userDetailsService.createUser(userDetails);
            }
        }
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        if(authenticationMethod.equals("NONE")) {
            httpSecurity
                    .authorizeRequests().antMatchers("/").permitAll()
                    .and()
                    .authorizeRequests().antMatchers("/console/**").permitAll();
        }else if(authenticationMethod.equals("IN_MEMORY")) {
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
        } else if(authenticationMethod.equals("LDAP")) {
            httpSecurity
                    .authorizeRequests().antMatchers("/static/**").permitAll()
                    .and()
                    .authorizeRequests().antMatchers("/login**").permitAll()
                    .and()
                    .authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated();
        } else if(authenticationMethod.equals("DATABASE")) {
            httpSecurity
                    .authorizeRequests().antMatchers("/static/**").permitAll()
                    .and()
                    .authorizeRequests().antMatchers("/login**").permitAll()
                    .and()
                    .authorizeRequests().antMatchers("/admin/**").hasAuthority("USER")
                    .anyRequest().authenticated();
        }

        //region LOGIN PAGE
        httpSecurity
                //loginPage is the url to login
                //loginProcessingUrl is the action on the login form in the jsp
                .formLogin().loginPage("/login").loginProcessingUrl("/login.do")
                //defaultSuccessUrl is where to go once login successful
                //failureUrl goes back to login page but assgin err=1 to be used for erro
                .defaultSuccessUrl("/", true).failureUrl("/login?err=1")
                .usernameParameter("username").passwordParameter("password");
        //endregion


        //region ADVANCED SETTINGS
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
        //endregion
    }

    @Bean
    public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
        ActiveDirectoryLdapAuthenticationProvider authenticationProvider =
                new ActiveDirectoryLdapAuthenticationProvider(ldapDomain, ldapUrl);

        authenticationProvider.setConvertSubErrorCodesToExceptions(true);
        authenticationProvider.setUseAuthenticationRequestCredentials(true);

        return authenticationProvider;
    }
}
