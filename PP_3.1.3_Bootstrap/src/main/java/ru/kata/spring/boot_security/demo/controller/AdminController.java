package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String printAllUsers(ModelMap model, Principal principal) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("activeUser", userService.loadByUsername(principal.getName()));
        return "users";
    }

    @PostMapping("/users")
    public String createNewUser(@ModelAttribute("newUser") User newUser,
                                @RequestParam(required = false) String roleAdd) {
        newUser.getRoles().add(roleService.getRole(2));
        if (roleAdd != null && roleAdd.equals("ADMIN")) {
            newUser.getRoles().add(roleService.getRole(1));
        }
        userService.setUser(newUser);
        return "redirect:/admin/users";
    }

    @PatchMapping("/update")
    public String updateOldUser(@ModelAttribute(name = "user") User user,
                                @RequestParam(required = false) String roleEdit) {
        user.getRoles().add(roleService.getRole(2));
        if (roleEdit != null && roleEdit.equals("ADMIN")) {
            user.getRoles().add(roleService.getRole(1));
        }
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/delete")
    public String deleteOldUser(@ModelAttribute(name = "user") User user) {
        userService.deleteUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/getUser")
    @ResponseBody
    public User getUser(int id) {
        return userService.getUser(id);
    }

    @GetMapping("/myInfo")
    public String showUserInfo(Principal principal, Model model) {
        model.addAttribute("activeUser", userService.loadByUsername(principal.getName()));
        return "user";
    }
}