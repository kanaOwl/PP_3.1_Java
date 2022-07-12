package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.*;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    void updateUser(User user);

    User getUser(int id);

    void deleteUser(int id);

    void setUser(User user);

    UserDetails searchByUsername(String s);

    User searchByUsernameOfUser(String s);

    void setUserTest(User user);

    User roleExistenceCheck(User user, String role);
}
