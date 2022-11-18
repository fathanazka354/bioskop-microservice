package com.cinema.authmicroservice.service;

import com.cinema.authmicroservice.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    User saveUser(User user);
}
