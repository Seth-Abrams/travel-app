package com.sethabrams.travelapp.controllers;

import com.sethabrams.travelapp.domain.User;
import com.sethabrams.travelapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User getUserById(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new NullPointerException();
        }
        return user.get();
    }

    @PostMapping(path = "/users/")
    public User createUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping(path = "/users/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User userUpdate){
        return userRepository.findById(id)
                .map(user -> {
                user.setName(userUpdate.getName());
                user.setEmail(userUpdate.getEmail());
                return userRepository.save(user);
                }).orElseThrow(ResourceNotFoundException::new);
    }


    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(ResourceNotFoundException::new);
    }
}
