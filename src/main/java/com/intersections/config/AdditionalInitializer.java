package com.intersections.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.WebApplicationInitializer;

/**
 *
 * @author Cvele
 */
public class AdditionalInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        
        FilterRegistration.Dynamic filter = servletContext.addFilter("hibernateFilter", OpenSessionInViewFilter.class);
        filter.setInitParameter("sessionFactoryBeanName", "sessionFactory");
        filter.addMappingForUrlPatterns(null, false, "/*");
        
        servletContext.addListener(HttpSessionEventPublisher.class);
        servletContext.addListener(SessionListener.class);
    }
    
}
