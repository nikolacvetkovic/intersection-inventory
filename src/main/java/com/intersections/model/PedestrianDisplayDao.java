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
public class PedestrianDisplayDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public PedestrianDisplay getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        PedestrianDisplay pd = (PedestrianDisplay)session.get(PedestrianDisplay.class, id);
        return pd;
    }
    public List<PedestrianDisplay> getByPole(Pole p) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(PedestrianDisplay.class);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        c.add(Restrictions.eq("pole", p));
        c.addOrder(Order.asc("symbol"));
        List<PedestrianDisplay> list = c.list();
        
        return list;
    }
    public void insert(PedestrianDisplay pd){
        Session session = sessionFactory.getCurrentSession();
        session.save(pd);
    }
    public void update(PedestrianDisplay pd){
        Session session = sessionFactory.getCurrentSession();
        session.update(pd);
    }
}
