package com.intersections.model.dao;

import com.intersections.model.Access;
import com.intersections.model.Intersection;
import com.intersections.model.Pole;
import com.intersections.model.TrafficSignalController;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class TrafficSignalControllerDao extends DaoClass<TrafficSignalController, Intersection, Access, Pole, Integer>{
    
}
