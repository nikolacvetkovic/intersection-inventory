package com.intersections.model;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class IntersectionDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public List<Intersection> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(Intersection.class);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        c.addOrder(Order.asc("symbol"));
        List<Intersection> list = c.list();
        return list;
    }
    public Intersection getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Intersection i = (Intersection)session.get(Intersection.class, id);
        return i;
    }
    public void insert(Intersection i){
        Session session = sessionFactory.getCurrentSession();
        session.save(i);
    }
    public void update(Intersection i){
        Session session = sessionFactory.getCurrentSession();
        session.update(i);
    }
    
}
