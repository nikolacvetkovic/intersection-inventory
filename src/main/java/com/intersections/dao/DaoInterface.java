package com.intersections.dao;

import java.io.Serializable;
import java.util.List;

public interface DaoInterface<T, I, A, P, ID extends Serializable > {
    
    public List<T> getAll();
    
    public T getById(ID id);
    
    public List<T> getByIntersection(I i);
    
    public List<T> getByAccess(A a);
    
    public List<T> getByPole(P p);
    
    public void insert(T t);
    
    public void update(T t);
    
}
