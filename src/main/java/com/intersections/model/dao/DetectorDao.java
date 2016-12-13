package com.intersections.model.dao;

import com.intersections.model.Access;
import com.intersections.model.Detector;
import com.intersections.model.Intersection;
import com.intersections.model.Pole;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class DetectorDao extends DaoClass<Detector, Intersection, Access, Pole, Integer>{
    
}
