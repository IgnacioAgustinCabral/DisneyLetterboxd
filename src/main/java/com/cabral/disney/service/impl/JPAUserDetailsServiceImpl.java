package com.cabral.disney.service.impl;

import com.cabral.disney.entity.User;
import com.cabral.disney.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JPAUserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public JPAUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        return user.get();
    }
}