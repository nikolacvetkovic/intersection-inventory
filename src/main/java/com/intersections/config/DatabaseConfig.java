package com.intersections.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Cvele
 */

@Configuration
@EnableTransactionManagement
@PropertySources({@PropertySource("/WEB-INF/jdbc.properties")})
//@ImportResource("/WEB-INF/spring/database.xml")
public class DatabaseConfig {
        
    @Autowired
    Environment env;
    
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        driverManagerDataSource.setUsername(env.getProperty("jdbc.username"));
        driverManagerDataSource.setPassword(env.getProperty("jdbc.password"));
        driverManagerDataSource.setUrl(env.getProperty("jdbc.url"));
        
        return driverManagerDataSource;
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        Properties prop = new Properties();
        prop.setProperty("dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(dataSource);
        lsfb.setHibernateProperties(prop);
        lsfb.setPackagesToScan("com.intersections.model");
        
        return lsfb;
    }
    
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        
        return transactionManager;
    }
    
}
