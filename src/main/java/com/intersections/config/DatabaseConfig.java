package com.intersections.config;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Cvele
 */

@Configuration
@EnableTransactionManagement
@PropertySources({@PropertySource("classpath:database.properties")})
//@ImportResource("/WEB-INF/spring/database.xml")
public class DatabaseConfig {
        
    @Autowired
    Environment env;
    
    @Profile("dev")
    @Bean
    public DataSource devDataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                // need scripts
                .addScript("")
                .addScript("")
                .build();
    }
    
    @Profile("test")
    @Bean
    public DataSource testDataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(env.getProperty("testDatabase.driverClassName"));
        driverManagerDataSource.setUsername(env.getProperty("testDatabase.username"));
        driverManagerDataSource.setPassword(env.getProperty("testDatabase.password"));
        driverManagerDataSource.setUrl(env.getProperty("testDatabase.url"));
        
        return driverManagerDataSource;
    }
    
    @Profile("prod")
    @Bean
    public DataSource jndiDataSource(){
        JndiObjectFactoryBean jndi = new JndiObjectFactoryBean();
        jndi.setResourceRef(true);
        jndi.setJndiName(env.getProperty("prodDatabase.jndiName"));
        jndi.setProxyInterface(DataSource.class);
        try {
            jndi.afterPropertiesSet();
        } catch (IllegalArgumentException | NamingException ex) {
            Logger.getLogger(DatabaseConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (DataSource) jndi.getObject();
    }
    
    @Profile({"test", "prod"})
    @Bean
    public SessionFactory sessionFactory(DataSource dataSource){
        Properties prop = new Properties();
        prop.setProperty("hibernate.dialect", env.getProperty("mysql.hibernate.dialect"));
        prop.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        prop.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(dataSource);
        lsfb.setPackagesToScan("com.intersections.model");
        lsfb.setHibernateProperties(prop);
        try {
            lsfb.afterPropertiesSet();
        } catch (IOException ex) {
            Logger.getLogger(DatabaseConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lsfb.getObject();
    }
    
    @Profile({"dev"})
    @Bean
    public SessionFactory devSessionFactory(DataSource dataSource){
        Properties prop = new Properties();
        prop.setProperty("hibernate.dialect", env.getProperty("h2.hibernate.dialect"));
        prop.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        prop.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(dataSource);
        lsfb.setPackagesToScan("com.intersections.model");
        lsfb.setHibernateProperties(prop);
        try {
            lsfb.afterPropertiesSet();
        } catch (IOException ex) {
            Logger.getLogger(DatabaseConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lsfb.getObject();
    }
    
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        
        return transactionManager;
    }
    
}
