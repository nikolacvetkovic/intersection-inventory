package com.intersections.config;

import com.intersections.dao.IntersectionDao;
import com.intersections.excelservice.ExportExcel;
import com.intersections.model.Intersection;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Cvele
 */
@Configuration
@ComponentScan(basePackageClasses = {IntersectionDao.class, ExportExcel.class, Intersection.class, WebConfig.class})
public class RootConfig {
    
}
