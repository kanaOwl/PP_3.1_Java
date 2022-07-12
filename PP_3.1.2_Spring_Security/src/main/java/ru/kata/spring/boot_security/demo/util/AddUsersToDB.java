package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class AddUsersToDB {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AddUsersToDB(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void createUsersWithRoles() {
        try {
            Role checkRole = roleService.getRole("ADMIN");
        } catch (Exception e) {
            roleService.setRole(new Role("ADMIN"));
        }
        try {
            Role checkRole = roleService.getRole("USER");
        } catch (Exception e) {
            roleService.setRole(new Role("USER"));
        }
        try {
            User user = userService.searchByUsernameOfUser("Ivan");
        } catch (Exception e) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRole("ADMIN"));
            userService.setUserTest(new User("Ivan", "Ivanov", "1","1", roles));
        }
        try {
            User user = userService.searchByUsernameOfUser("Alex");
        } catch (Exception e) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRole("USER"));
            userService.setUserTest(new User("Alex", "Alexeev", "2", "2", roles));
        }

    }
}
