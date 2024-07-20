package com.htfc786.wydb.controller;

import com.htfc786.wydb.entity.User;
import com.htfc786.wydb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public User getUser(@RequestParam("id") int id) {
        return userService.queryUser(id);
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return userService.queryUser();
    }
}
