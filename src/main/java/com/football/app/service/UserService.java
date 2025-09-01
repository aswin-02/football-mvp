package com.football.app.service;

import com.football.app.entity.User;
import java.util.Optional;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByMobile(String mobile);
    List<User> getAllActiveUsers();
}
