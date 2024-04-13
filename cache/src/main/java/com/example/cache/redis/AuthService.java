package com.example.cache.redis;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService
{
    private final List<User> users = new ArrayList<>();

    {
        users.add(new User("user", "user"));
        users.add(new User("admin", "admin"));
    }

    public boolean isAuth (User user) {
        return users.contains(user);
    }
}
