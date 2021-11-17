package com.bankit.service;

import com.bankit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<com.bankit.model.User, Long> {
}
