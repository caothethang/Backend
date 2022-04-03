package com.ecommerce.root.services;


import com.ecommerce.root.entity.User;
import com.ecommerce.root.repositories.UserRepository;
import com.ecommerce.root.security.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)){
            throw new UsernameNotFoundException("User not found with username: "+username);
        }
        return UserDetailsImpl.build(user);
    }
}
