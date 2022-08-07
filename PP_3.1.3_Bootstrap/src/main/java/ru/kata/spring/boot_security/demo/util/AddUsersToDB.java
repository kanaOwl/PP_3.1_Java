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

        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");

        roleService.setRole(role1);
        roleService.setRole(role2);

        Set<Role> roles1 = new HashSet<>();
        roles1.add(role1);
        roles1.add(role2);
        userService.setUser(new User("Ivan", "Ivanov", 23, "admin@mail.ru","admin", roles1));

        Set<Role> roles2 = new HashSet<>();
        roles2.add(role2);
        userService.setUser(new User("Oleg", "Olegov", 39, "user@mail.ru", "user", roles2));
    }
}
