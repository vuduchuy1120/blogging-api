package com.example.bloggingaplication.security;

import com.example.bloggingaplication.entity.User;
import com.example.bloggingaplication.exceptions.ResourceNotFoundException;
import com.example.bloggingaplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //loading user from database by username
        User user = this.userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User ", " email: " + username, 0));

        return user;
    }

}
