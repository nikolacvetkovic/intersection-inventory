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
public class SignalHeadDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public SignalHead getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        SignalHead sg = (SignalHead)session.get(SignalHead.class, id);
        return sg;
    }
    public List<SignalHead> getByPole(Pole p) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(SignalHead.class);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        c.add(Restrictions.eq("pole", p));
        c.addOrder(Order.asc("symbol"));
        List<SignalHead> list = c.list();
        
        return list;
    }
    public void insert(SignalHead sg){
        Session session = sessionFactory.getCurrentSession();
        session.save(sg);
    }
    public void update(SignalHead sg){
        Session session = sessionFactory.getCurrentSession();
        session.update(sg);
    }
}
