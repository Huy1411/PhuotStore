package com.example.phuotstore.service;


import com.example.phuotstore.model.Category;
import com.example.phuotstore.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService  {
    void save(User user);
    User findByUsername(String username);
    List<User> getAllUsers();
    User getUserByID(long userID);
    boolean saveUser(User user);
    boolean deleteUser(long userID);
    boolean updateUser(User user);
    boolean checkUserName(String username);
    Page<User> findPaginated(int pageNo, int pageSize);
}
