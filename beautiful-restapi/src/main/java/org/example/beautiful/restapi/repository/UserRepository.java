package org.example.beautiful.restapi.repository;

import org.example.beautiful.restapi.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    // CRUD insert、select、update、delete

    public void insert(User user) {
        System.out.println(user.toString());
    }
}
