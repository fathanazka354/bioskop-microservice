package com.cinema.usermicroservice.service;

import com.cinema.usermicroservice.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    User saveUser(User user);
}
