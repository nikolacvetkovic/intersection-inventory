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
public class PedestrianPushButtonDao {
    
    @Autowired
    SessionFactory sessionFactory;
    
    public PedestrianPushButton getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        PedestrianPushButton ppb = (PedestrianPushButton)session.get(PedestrianPushButton.class, id);
        return ppb;
    }
    public List<PedestrianPushButton> getByPole(Pole p) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(PedestrianPushButton.class);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        c.add(Restrictions.eq("pole", p));
        c.addOrder(Order.asc("symbol"));
        List<PedestrianPushButton> list = c.list();
        
        return list;
    }
    public void insert(PedestrianPushButton ppb){
        Session session = sessionFactory.getCurrentSession();
        session.save(ppb);
    }
    public void update(PedestrianPushButton ppb){
        Session session = sessionFactory.getCurrentSession();
        session.update(ppb);
    }
}
