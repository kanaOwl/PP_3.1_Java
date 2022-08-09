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

//        Role role1 = roleService.getRole(1);
//        Role role2 = roleService.getRole(2);
//
//        roleService.setRole(role1);
//        roleService.setRole(role2);
//
//        Set<Role> roles1 = new HashSet<>();
//        roles1.add(role1);
//        roles1.add(role2);
//        userService.setUser(new User("Ivan", "Ivanov", "admin@mail.ru","admin", roles1));
//
//        Set<Role> roles2 = new HashSet<>();
//        roles2.add(role2);
//        userService.setUser(new User("Oleg", "Olegov", "user@mail.ru", "user", roles2));

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
            userService.setUser(new User("Ivan", "Ivanov", "1","1", roles));
        }
        try {
            User user = userService.loadByUsername("Oleg");
        } catch (Exception e) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRole(2));
            userService.setUser(new User("Oleg", "Olegov", "2", "2", roles));
        }
    }
}
