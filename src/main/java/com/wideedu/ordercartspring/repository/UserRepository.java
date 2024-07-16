package com.wideedu.ordercartspring.repository;

import com.wideedu.ordercartspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
