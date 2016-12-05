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
public class PoleDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public List<Pole> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(Pole.class);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        c.addOrder(Order.asc("symbol"));
        List<Pole> list = c.list();
        
        return list;
    }
    public Pole getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Pole p = (Pole)session.get(Pole.class, id);
        return p;
    }
    public List<Pole> getByAccess(Access a) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(Pole.class);
        c.add(Restrictions.eq("access", a));
        c.addOrder(Order.asc("symbol"));
        List<Pole> list = c.list();
        
        return list;
    }
    public List<Pole> getByIntersection(Intersection i) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(Pole.class);
        c.add(Restrictions.eq("intersection", i));
        c.addOrder(Order.asc("symbol"));
        List<Pole> list = c.list();
        return list;
    }
    public void insert(Pole p){
        Session session = sessionFactory.getCurrentSession();
        session.save(p);
    }
    public void update(Pole p){
        Session session = sessionFactory.getCurrentSession();
        session.update(p);
    }
    
}
