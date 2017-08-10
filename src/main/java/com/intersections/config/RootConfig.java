package com.intersections.config;

import com.intersections.dao.IntersectionDao;
import com.intersections.excelservice.ExportExcel;
import com.intersections.model.Intersection;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author Cvele
 */
@Configuration
@ComponentScan(basePackageClasses = {IntersectionDao.class, ExportExcel.class, Intersection.class, WebConfig.class})
@ImportResource(locations = "/WEB-INF/spring/database.xml")
public class RootConfig {
    
}
