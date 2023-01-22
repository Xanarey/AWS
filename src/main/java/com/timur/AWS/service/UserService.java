package com.timur.AWS.service;

import com.timur.AWS.model.User;

import java.util.List;

public interface UserService {

    User getById(Long id);

    List<User> getAllUsers();

    User save(User user);

    void delete(User user);

    void update(User user);

}
