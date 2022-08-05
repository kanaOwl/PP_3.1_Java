package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.*;
import ru.kata.spring.boot_security.demo.model.*;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(
                userService.getUsers(),
                HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        return new ResponseEntity<>(
                userService.getUser(id),
                HttpStatus.OK);
    }

    @GetMapping("/roles/all")
    public ResponseEntity<List<Role>> getRoles() {
        return new ResponseEntity<>(
                roleService.getRoles(),
                HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Integer id) {
        return new ResponseEntity<>(
                roleService.getRole(id),
                HttpStatus.OK);
    }

    @GetMapping("/users/all_information_about_users")
    public ResponseEntity<User> getActiveUser(Principal principal) {
        return new ResponseEntity<>(
                userService.loadByUsername(principal.getName()),
                HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> setUser(@RequestBody User user) {
        userService.setUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/roles/about_user/{id}")
    public ResponseEntity<String> getRolesOfUser(@PathVariable Integer id) {
        return new ResponseEntity<>(
                userService.getUser(id).toStringRoles(),
                HttpStatus.OK);
    }
}
