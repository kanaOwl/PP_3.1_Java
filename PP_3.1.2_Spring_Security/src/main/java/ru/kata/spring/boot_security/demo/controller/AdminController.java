package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.*;
import ru.kata.spring.boot_security.demo.service.*;

import java.security.Principal;

@Controller

public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public String displayAllUsers(ModelMap model, Principal principal) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("checkingUser", userService.loadByUsername(principal.getName()));
        return "users";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @PatchMapping("/admin/edit/{id}")
    public String saveEditUser(@ModelAttribute("user") User existingUser,
                               @RequestParam(required = false) String role) {
        userService.updateUser(userService.roleExistenceCheck(existingUser, role));
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "delete_user";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String saveDeletionUser(@PathVariable(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/create")
    public String addNewUser(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("user", user);
        return "new_user";
    }

    @PostMapping("/admin/create")
    public String createNewUser(@ModelAttribute("user") User newUser,
                                @RequestParam(required = false) String addRole) {
        userService.setUser(userService.roleExistenceCheck(newUser, addRole));
        return "redirect:/admin/users";
    }
}