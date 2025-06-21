package com.jeydot.springskillup.service;

import com.jeydot.springskillup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class QuizUserDetailsService implements UserDetailsService {
    private final HashMap<String, User> users = new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
    @Autowired
    public void registerUser(String username, String password, PasswordEncoder passwordEncoder) throws Exception {
        if (users.containsKey(username)) {
            throw new Exception("Username already exists");
        }
        users.put(username, new User(username, username + "@email.com", passwordEncoder(password), "USER"));
    }
}
