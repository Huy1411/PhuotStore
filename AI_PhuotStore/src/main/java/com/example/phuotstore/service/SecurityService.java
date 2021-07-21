package com.example.phuotstore.service;

public interface SecurityService {
    String findLonggedInUsername();
    void autoLogin(String username, String password);
}
