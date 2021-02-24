package com.laioffer.travel_planner_backend.service;


import com.laioffer.travel_planner_backend.entity.User;
import com.laioffer.travel_planner_backend.entity.UserPrinciple;
import com.laioffer.travel_planner_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    UserRepository userRepository;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        
        User user = userRepository.findByUsername(username)
            .orElseThrow(() ->
                new UsernameNotFoundException("User Not Found with -> username : " + username)
            );
        
        return UserPrinciple.build(user);
    }
    
    
    @Transactional
    public UserDetails loadUserByEmail(String email)
        throws UsernameNotFoundException {
        
        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new UsernameNotFoundException("User Not Found with -> email : " + email)
            );
        
        return UserPrinciple.build(user);
    }
    
    @Transactional
    public User getUserByEmail(String email)
        throws UsernameNotFoundException {
        
        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new UsernameNotFoundException("User Not Found with -> email : " + email)
            );
        
        return user;
    }
    
    @Transactional
    public User getUserByUsername(String username)
        throws UsernameNotFoundException {
        
        User user = userRepository.findByUsername(username)
            .orElseThrow(() ->
                new UsernameNotFoundException("User Not Found with -> email : " + username)
            );
        
        return user;
    }
    
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
}