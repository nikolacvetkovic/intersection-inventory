package com.intersections.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Cvele
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    UserDetailsService userDao;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/insert/**", "/update/**").hasRole("FULLUSER")
                .antMatchers("/pdf/**", "/use/**").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                    .accessDeniedPage("/403")
                .and()
                .formLogin()
                    .loginPage("/login")
                        .defaultSuccessUrl("/")
                .and()
                .logout()
                    .logoutSuccessUrl("/login")
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .maximumSessions(1)
                            .maxSessionsPreventsLogin(true)
                                .expiredUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDao)
                .passwordEncoder(new BCryptPasswordEncoder(4));
    }
    
    
    
}
