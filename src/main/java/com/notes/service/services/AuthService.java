package com.notes.service.services;

import com.notes.dao.entites.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface AuthService {
    public PasswordEncoder getEncoder();

    public UserDetails loadUserByUsername(String email);

    public Object register(User user);
}
