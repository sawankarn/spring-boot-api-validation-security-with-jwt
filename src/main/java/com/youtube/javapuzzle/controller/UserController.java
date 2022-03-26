package com.youtube.javapuzzle.controller;

import com.youtube.javapuzzle.entity.User;
import com.youtube.javapuzzle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users") //ADMIN and EDITOR
    @PreAuthorize("hasAuthority('ADMIN')")
    List<User> findAllUsers(){
       return userService.findAllUsers();
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAnyAuthority('EDITOR','ADMIN')")
    ResponseEntity<User> findByUserId(@PathVariable("userId") @Min(1) int userId){
        return userService.findByUserId(userId);
    }

    @PostMapping("/users") //ADMIN
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<User> createUser(@RequestBody @Valid User user){
        return userService.createUser(user);
    }

    @PutMapping("/users/{userId}")
    ResponseEntity<User> updateUser(@RequestBody @Valid User user, @PathVariable("userId") @Min(1) int userId){
        return userService.updateUser(user, userId);
    }

    @DeleteMapping("/users/{userId}")
    ResponseEntity<String> deleteUserById(@PathVariable @Min(1) int userId){
        return userService.deleteUserById(userId);
    }

}
