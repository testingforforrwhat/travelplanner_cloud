package com.test.travelplanner.security;


import com.test.travelplanner.repository.UserEntityRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserEntityRepository userRepository;


    public UserDetailsServiceImpl(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userDetails;
    }
}