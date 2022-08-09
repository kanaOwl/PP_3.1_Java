package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    void updateUser(User user);

    User getUser(int id);

    void deleteUser(int id);

    void setUser(User user);

    User loadByUsername(String s);
}
