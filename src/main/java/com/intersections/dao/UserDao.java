package com.intersections.dao;

import com.intersections.model.Rank;
import com.intersections.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class UserDao extends UserAbstractDao<User, Integer> implements UserDetailsService{
    
    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> auth = new ArrayList<>();
        for(Rank r : user.getRankList()){
            auth.add(new SimpleGrantedAuthority("ROLE_" + r.getType()));
        }
        
        return auth;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUserName(username);
        
        if(user==null){
            throw new UsernameNotFoundException(username);
        }
        
        org.springframework.security.core.userdetails.UserDetails u = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPass(), "Active".equals(user.getState()), true, true, true, getGrantedAuthorities(user));
        return u;
    }
    
}
