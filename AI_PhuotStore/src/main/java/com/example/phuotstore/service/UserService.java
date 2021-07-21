package com.example.phuotstore.service;


import com.example.phuotstore.model.User;

public interface UserService  {
    void save(User user);
    User findByUsername(String username);
}
