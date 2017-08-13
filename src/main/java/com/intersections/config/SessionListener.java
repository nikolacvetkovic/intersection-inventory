package com.intersections.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 *
 * @author Cvele
 */
public class SessionListener implements HttpSessionListener{

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setMaxInactiveInterval(30*60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        
    }
    
}
