package ch.bbw.ap.quizbackend.controller;

import ch.bbw.ap.quizbackend.model.User;
import ch.bbw.ap.quizbackend.model.UserWithCredentials;
import ch.bbw.ap.quizbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // ------------------------------------------------------------------------------
    // Login
    // -------------------------------------------------------------------------------
    @PostMapping(value = "/registrate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User registrate(@RequestBody UserWithCredentials user) throws NoSuchAlgorithmException {
        return userService.createUser(user);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> login(@RequestBody UserWithCredentials user) {
        return userService.login(user);
    }


    @GetMapping("/user")
    public User getUser() {
        return userService.getUser();
    }

    @PutMapping("/user")
    public Map<String, User> editUser(@RequestBody User user) {
        return userService.editUser(user);
    }

    @DeleteMapping("/user")
    public User deleteUser() {
        return userService.deleteUser();
    }
}
