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
            Role checkRole = roleService.getRole(1);
        } catch (Exception e) {
            roleService.setRole(new Role("ADMIN"));
        }
        try {
            Role checkRole = roleService.getRole(2);
        } catch (Exception e) {
            roleService.setRole(new Role("USER"));
        }
        try {
            User user = userService.loadByUsername("Ivan");
        } catch (Exception e) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRole(1));
            roles.add(roleService.getRole(2));
            userService.setUser(new User("Ivan", "Ivanov", 40, "admin@mail.ru","admin", roles));
        }
        try {
            User user = userService.loadByUsername("Oleg");
        } catch (Exception e) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRole(2));
            userService.setUser(new User("Oleg", "Olegov", 23,"user@mail.ru", "user", roles));
        }

    }
}
