package com.kata311.controller;

import com.kata311.model.User;
import com.kata311.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //открытие окна всех пользователей
    @GetMapping("/users")
    public String printAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    //открытие окна добавления нового пользователя
    @GetMapping("/new_user")
    public String addNewUser(Model model, @ModelAttribute(name = "user") User user) {
        model.addAttribute("user", user);
        return "new_user";
    }

    //сохранение нового пользователя
    @PostMapping("/users")
    public String saveNewUser(@ModelAttribute(name = "user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    //редактирование пользователя
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    //сохранение редактирования пользователя
    @PatchMapping("/{id}")
    public String saveEditUser(@PathVariable(name = "id") int id,
                               @ModelAttribute(name = "user") User user) {
        userService.updateUser(user, id);
        return "redirect:/users";
    }

    //удаление пользователя
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "delete_user";
    }

    //сохранение удаления пользователя
    @PostMapping("/{id}")
    public String saveDeleteUser(@PathVariable(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}