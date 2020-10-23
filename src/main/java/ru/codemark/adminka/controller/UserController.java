package ru.codemark.adminka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.codemark.adminka.model.User;
import ru.codemark.adminka.respons.Respons;
import ru.codemark.adminka.service.UserService;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public List<User> findUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/api/find")
    public User findUserByLogin(@RequestBody User user) {
        return userService.findUserByLogin(user.getLogin());
    }

    @DeleteMapping("/api/user")
    public Respons deleteUser(@RequestBody User user) {
        return userService.deleteUser(user);
    }

    @PostMapping("/api/user")
    public ResponseEntity<Respons> addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            List<String> err = errors.getAllErrors().stream()
                     .map(e -> e.getDefaultMessage())
                     .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(new Respons(false, err));
        }

        return new ResponseEntity(new Respons(true), HttpStatus.OK);
    }

    @PutMapping("/api/user")
    public ResponseEntity<Respons> updateUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            List<String> err = errors.getAllErrors().stream()
                    .map(e -> e.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(new Respons(false, err));
        }
        return new ResponseEntity<Respons>(new Respons(true), HttpStatus.OK);
    }
}
