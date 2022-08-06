package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.updateUser(user);
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(getUser(id));
    }

    @Override
    @Transactional
    public void setUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.setUser(user);
    }

    @Override
    public User loadByUsername(String username) throws UsernameNotFoundException {
        return userDao.loadByUsername(username);
    }

    @Override
    @Transactional
    public User roleExistenceCheck(User user, String role){
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRole(1));
        if (role != null) {
            roles.add(roleService.getRole(2));
        }
        user.setRoles(roles);
        return user;
    }
}
