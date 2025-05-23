package com.example.demo.mapper;// package com.example.demo.mapper;


import com.example.demo.entity.Payloads.UserPayload;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserPayload payload) {
        return new User(null, payload.name(), payload.email(), payload.age());
    }

    public void updateEntity(User user, UserPayload payload) {
        user.setName(payload.name());
        user.setEmail(payload.email());
        user.setAge(payload.age());
    }
}