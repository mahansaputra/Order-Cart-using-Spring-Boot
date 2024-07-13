package com.wideedu.ordercartspring.service;

import com.wideedu.ordercartspring.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    User saveUser(User user);
    User findByUsername(String username);
    User getUserById(long id);
}
