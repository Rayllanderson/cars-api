package com.rayllanderson.cars.api.security;

import com.rayllanderson.cars.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.rayllanderson.cars.domain.entities.User user =
                repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));

        return User.withUsername(username).password(user.getPassword()).roles("USER").build();
    }
}
