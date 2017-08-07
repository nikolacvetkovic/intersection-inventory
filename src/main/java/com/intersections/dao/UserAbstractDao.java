package com.intersections.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class UserAbstractDao<U, ID extends Serializable> implements UserInterface<U, ID>{

    @Autowired
    private SessionFactory sessionFactory;
    private Class<U> classType;
    
    public UserAbstractDao(){
        ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
        this.classType = (Class<U>) type.getActualTypeArguments()[0];
    }
    
    @Override
    public void insert(U u) {
        Session session = sessionFactory.getCurrentSession();
        session.save(u);
    }

    @Override
    public void update(U u) {
        Session session = sessionFactory.getCurrentSession();
        session.update(u);
    }

    @Override
    public void delete(U u) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(u);
    }

    @Override
    public U getById(ID id) {
        Session session = sessionFactory.getCurrentSession();
        U u = (U)session.get(classType, id);
        return u;
    }

    @Override
    public U getByUserName(String username) {
        Session session = sessionFactory.getCurrentSession();
        Criteria c = session.createCriteria(classType);
        c.add(Restrictions.eq("username", username));
        U u = (U)c.uniqueResult();
        return u;
    }
    
}
