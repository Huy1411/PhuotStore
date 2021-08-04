package com.example.phuotstore.service;

public interface SecurityService {
    String findLonggedInUserName();
    void autoLogin(String username, String password);
}
