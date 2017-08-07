package com.intersections.dao;

import com.intersections.model.PedestrianDisplay;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PedestrianDisplayDao extends AbstractDao<PedestrianDisplay, Integer>{
    
}
