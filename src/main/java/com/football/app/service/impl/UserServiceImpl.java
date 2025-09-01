package com.football.app.service.impl;

import com.football.app.entity.User;
import com.football.app.repository.UserRepository;
import com.football.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getUserByMobile(String mobile) {
        return userRepository.findByMobile(mobile);
    }

    @Override
    public List<User> getAllActiveUsers() {
        return userRepository.findAll().stream().filter(User::getIsActive).toList();
    }
}
