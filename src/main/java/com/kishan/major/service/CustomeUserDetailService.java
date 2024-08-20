package com.kishan.major.service;

import com.kishan.major.model.CustomeUserDetail;
import com.kishan.major.model.User;
import com.kishan.major.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomeUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =userRepository.findUserByEmail(username);
        user.orElseThrow(()->new UsernameNotFoundException("User not present"));
        return  user.map(CustomeUserDetail::new).get();
    }
}
