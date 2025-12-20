package com.example.demo.Service;

import com.example.demo.Entity.User;

public interface UserService {

    User register(User user);

    User findByEmail(String email);

    User getUser(Long id);
}
