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
public class AccessDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public List<Access> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(Access.class);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        c.addOrder(Order.asc("symbol"));
        List<Access> list = c.list();
        
        return list;
    }
    public Access getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Access a = (Access)session.get(Access.class, id);
        return a;
    }
    public List<Access> getByIntersection(Intersection i) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(Access.class);
        c.add(Restrictions.eq("intersection", i));
        c.addOrder(Order.asc("symbol"));
        List<Access> list = c.list();
        
        return list;
    }
    public void insert(Access a){
        Session session = sessionFactory.getCurrentSession();
        session.save(a);
    }
    public void update(Access a){
        Session session = sessionFactory.getCurrentSession();
        session.update(a);
    }
}
