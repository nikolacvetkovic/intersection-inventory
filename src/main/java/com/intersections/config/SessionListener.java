package com.intersections.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 *
 * @author Cvele
 */
public class SessionListener implements HttpSessionListener{

    @Autowired
    Environment env;
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(30*60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        
    }
    
}
