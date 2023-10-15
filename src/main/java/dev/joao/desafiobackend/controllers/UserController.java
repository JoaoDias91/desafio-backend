package dev.joao.desafiobackend.controllers;

import dev.joao.desafiobackend.domain.User;
import dev.joao.desafiobackend.dtos.UserDTO;
import dev.joao.desafiobackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        User createdUser = service.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
       List<User> usersList =  service.getAllUsers();
       return  new ResponseEntity<>(usersList, HttpStatus.OK);
    }
}
