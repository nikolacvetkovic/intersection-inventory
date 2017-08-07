package com.intersections.dao;

import com.intersections.model.SignalHead;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class SignalHeadDao extends AbstractDao<SignalHead, Integer>{
    
}
