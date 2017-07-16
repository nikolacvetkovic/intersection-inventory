package com.intersections.model;

public enum UserRank {
    ADMIN("ADMIN"),
    FULLUSER("FULLUSER"),
    USER("USER"),
    REMOTE("REMOTE");
    
    private String name;
    
    UserRank(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
