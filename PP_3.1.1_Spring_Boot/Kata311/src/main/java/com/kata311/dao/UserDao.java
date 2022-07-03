package com.kata311.dao;

import com.kata311.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void updateUser(User user, int id);

    void addUser(User user);

    void deleteUser(int id);

    User getUser(int id);
}
