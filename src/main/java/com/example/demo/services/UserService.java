package com.example.demo.services;

import com.example.demo.entity.Payloads.UserPayload;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUser(int id);
    Iterable<User> findUsers();
    User saveUser(UserPayload user);
    void updateUser(int id, UserPayload user);
    void deleteUser(int id);
}
