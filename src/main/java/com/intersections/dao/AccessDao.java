package com.intersections.dao;

import com.intersections.model.Access;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class AccessDao extends AbstractDao<Access, Integer>{

}
