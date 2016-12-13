package com.intersections.model.dao;

import com.intersections.model.Access;
import com.intersections.model.Intersection;
import com.intersections.model.PedestrianDisplay;
import com.intersections.model.Pole;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PedestrianDisplayDao extends DaoClass<PedestrianDisplay, Intersection, Access, Pole, Integer>{
    
}
