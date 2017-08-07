package com.intersections.dao;

import com.intersections.model.PedestrianPushButton;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PedestrianPushButtonDao extends AbstractDao<PedestrianPushButton, Integer>{
    
}
