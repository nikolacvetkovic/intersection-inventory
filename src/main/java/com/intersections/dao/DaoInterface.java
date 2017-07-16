package com.intersections.dao;

import com.intersections.model.Access;
import com.intersections.model.Intersection;
import com.intersections.model.Pole;
import java.io.Serializable;
import java.util.List;

public interface DaoInterface<T, ID extends Serializable > {
    
    public List<T> getAll();
    
    public T getById(ID id);
    
    public List<T> getByIntersection(Intersection i);
    
    public List<T> getByAccess(Access a);
    
    public List<T> getByPole(Pole p);
    
    public void insert(T t);
    
    public void update(T t);
    
}
