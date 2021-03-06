package com.rayllanderson.cars.api.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (username.equals("user"))
            return User.withUsername("user").password(encoder.encode("123")).roles("USER").build();
        if (username.equals("admin"))
            return User.withUsername("user").password(encoder.encode("123")).roles("USER", "ADMIN").build();

        throw new UsernameNotFoundException("user not found");
    }
}
