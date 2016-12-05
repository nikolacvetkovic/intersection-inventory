package com.intersections.model;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class TrafficSignalControllerDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public TrafficSignalController getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        TrafficSignalController tsc = (TrafficSignalController)session.get(TrafficSignalController.class, id);
        return tsc;
    }
    public TrafficSignalController getByIntersection(Intersection i) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(TrafficSignalController.class);
        c.add(Restrictions.eq("intersection", i));
        TrafficSignalController tsc = (TrafficSignalController) c.uniqueResult();
        return tsc;
    }
    public void insert(TrafficSignalController tsc){
        Session session = sessionFactory.getCurrentSession();
        session.save(tsc);
    }
    public void update(TrafficSignalController tsc){
        Session session = sessionFactory.getCurrentSession();
        session.update(tsc);
    }
}
