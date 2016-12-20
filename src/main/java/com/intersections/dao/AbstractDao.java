package com.intersections.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T, I, A, P, ID extends Serializable> implements DaoInterface<T, I, A, P, ID>{
    
    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> classType;
    
    public AbstractDao(){
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        this.classType = (Class<T>)type.getActualTypeArguments()[0];
    }
    
    @Override
    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(classType);
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        c.addOrder(Order.asc("symbol"));
        List<T> list = c.list();
        return list;
    }

    @Override
    public T getById(ID id) {
        Session session = sessionFactory.getCurrentSession();
        T a = (T)session.get(classType, id);
        return a;
    }

    @Override
    public List<T> getByIntersection(I i) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(classType);
        c.add(Restrictions.eq("intersection", i));
        c.addOrder(Order.asc("symbol"));
        List<T> list = c.list();
        return list;
    }

    @Override
    public List<T> getByAccess(A a) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(classType);
        c.add(Restrictions.eq("access", a));
        c.addOrder(Order.asc("symbol"));
        List<T> list = c.list();
        return list;
    }

    @Override
    public List<T> getByPole(P p) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(classType);
        c.add(Restrictions.eq("pole", p));
        c.addOrder(Order.asc("symbol"));
        List<T> list = c.list();
        return list;
    }
    
    @Override
    public void insert(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.save(t);
    }

    @Override
    public void update(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.update(t);
    }
    
}
