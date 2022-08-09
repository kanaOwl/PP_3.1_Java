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
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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
    public String updateUser(@ModelAttribute("user") User user,
                               @RequestParam(required = false) String roleEdit) {
        user.getRoles().add(roleService.getRole(2));
        if (roleEdit != null && roleEdit.equals("ADMIN")) {
            user.getRoles().add(roleService.getRole(1));
        }
        userService.updateUser(user);
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
                                @RequestParam(required = false) String roleAdd) {
        newUser.getRoles().add(roleService.getRole(2));
        if (roleAdd != null && roleAdd.equals("ADMIN")) {
            newUser.getRoles().add(roleService.getRole(1));
        }
        System.out.println(roleAdd);
        userService.setUser(newUser);
        return "redirect:/admin/users";
    }
}