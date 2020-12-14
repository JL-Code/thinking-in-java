package org.example.beautiful.restapi.service;

import org.example.beautiful.restapi.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String addUser(User user) {
        return "success";
    }
}
