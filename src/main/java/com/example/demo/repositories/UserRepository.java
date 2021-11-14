package com.example.demo.repositories;

import com.example.demo.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String value);

    User findByUsername(String value);

    boolean existsByUsername(String value);

    boolean existsByEmail(String value);

    User getByUsername(String name);
}
