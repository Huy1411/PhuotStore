package com.example.phuotstore.repository;

import com.example.phuotstore.model.Category;
import com.example.phuotstore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u")
    Page<User> getAllUsers(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.userID = ?1")
    User getUserByID(long userID);

    @Query("SELECT u FROM User u")
    List<User> getAllUsers();

    @Query("SELECT u FROM User u")
    Page<User> findPaginateUser(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUserName(String username);
}
