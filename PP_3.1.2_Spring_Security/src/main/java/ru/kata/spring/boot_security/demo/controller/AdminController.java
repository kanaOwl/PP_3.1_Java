package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.*;
import ru.kata.spring.boot_security.demo.service.*;

import java.security.Principal;

@Controller
@RequestMapping()
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/users")
    public String displayAllUsers(ModelMap model, Principal principal) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("checkingUser", userService.searchByUsernameOfUser(principal.getName()));
        return "users";
    }

    @GetMapping("/admin/edit/{id}")
    public String editUser(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @PatchMapping("/admin/{id}")
    public String saveEditUser(@ModelAttribute("user") User existingUser, @RequestParam(required = false) String role) {
        userService.updateUser(userService.roleExistenceCheck(existingUser, role));
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "delete_user";
    }

    @DeleteMapping("/admin/{id}")
    public String saveDeletionUser(@PathVariable(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/new_user")
    public String addNewUser(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("user", user);
        return "new_user";
    }

    @PostMapping("/admin/{id}")
    public String createNewUser(@ModelAttribute("user") User newUser, @RequestParam(required = false) String addRole) {
        userService.setUser(userService.roleExistenceCheck(newUser, addRole));
        return "redirect:/admin/users";
    }
}