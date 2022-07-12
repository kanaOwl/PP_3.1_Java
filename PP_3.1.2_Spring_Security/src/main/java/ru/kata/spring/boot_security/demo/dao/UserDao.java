package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.*;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    void updateUser(User user);

    User getUser(int id);

    void deleteUser(User user);

    void setUser(User user);

    UserDetails searchByUsername(String username);

    User searchByUsernameOfUser(String username);
}
