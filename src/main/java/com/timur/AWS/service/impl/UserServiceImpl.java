package com.timur.AWS.service.impl;

import com.timur.AWS.model.User;
import com.timur.AWS.repository.UserRepository;
import com.timur.AWS.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        log.info("IN UserServiceImpl findByUsername {}", username);
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User getById(Long id) {
        log.info("IN UserServiceImpl getById {}", id);
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public List<User> getAllUsers() {
        log.info("IN UserServiceImpl getAllUsers");
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        log.info("IN CustomerServiceImpl save {}", user);
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        log.info("IN UserServiceImpl deleteById {}", user);
        userRepository.delete(user);
    }

    @Override
    public void update(User user) {
        log.info("IN CustomerServiceImpl update {}", user);
        userRepository.save(user);
    }
}
