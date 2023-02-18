package com.monofi.repository;

import com.monofi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
