package com.intersections.dao;

import java.io.Serializable;

public interface UserInterface<U, ID extends Serializable> {
    
    public void insert(U u);
    
    public void update(U u);
    
    public void delete(U u);
    
    public U getByUserName(String username);
    
    public U getById(ID id);
}
