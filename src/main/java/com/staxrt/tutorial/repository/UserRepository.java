package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findById(int id);
    boolean existsByEmail(String email);
    List<User> findByFirstName(String firstName);
}
