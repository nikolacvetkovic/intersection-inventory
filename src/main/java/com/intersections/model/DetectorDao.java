package com.intersections.model;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class DetectorDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public Detector getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Detector a = (Detector)session.get(Detector.class, id);
        return a;
    }
    public List<Detector> getByAccess(Access a) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(Detector.class);
        c.add(Restrictions.eq("access", a));
        c.addOrder(Order.asc("symbol"));
        List<Detector> list = c.list();
        
        return list;
    }
    public void insert(Detector d){
        Session session = sessionFactory.getCurrentSession();
        session.save(d);
    }
    public void update(Detector d){
        Session session = sessionFactory.getCurrentSession();
        session.update(d);
    }
}
