package com.caseitau.repository;

import com.caseitau.entity.StatusTask;
import com.caseitau.entity.Task;
import com.caseitau.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    @Query(value = "SELECT t FROM User t WHERE t.name = ?1")
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
}
