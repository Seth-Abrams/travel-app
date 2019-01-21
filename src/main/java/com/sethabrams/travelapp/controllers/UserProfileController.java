package com.sethabrams.travelapp.controllers;

import com.sethabrams.travelapp.domain.UserProfile;
import com.sethabrams.travelapp.repositories.UserProfileRepository;
import com.sethabrams.travelapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserProfileController {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserProfileController(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/users/{userId}/profile")
    public UserProfile getProfileByUser(@PathVariable Long userId){
        return userProfileRepository.findByUserId(userId);
    }

    @PostMapping(path = "users/{userId}/profile")
    public UserProfile createProfile(@PathVariable Long userId, @Valid @RequestBody UserProfile userProfile){
        return userRepository.findById(userId)
                .map(user -> {
                    userProfile.setUser(user);
                    return userProfileRepository.save(userProfile);
                }).orElseThrow(ResourceNotFoundException::new);

    }

    @PutMapping(path = "users/{userId}/profile/{profileId}")
    public UserProfile updateProfile(@PathVariable Long userId,
                                     @PathVariable Long profileId,
                                     @Valid @RequestBody UserProfile profileUpdate){

        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException();
        }
        return userProfileRepository.findById(profileId)
                .map(userProfile -> {
                    userProfile.setUserRating(profileUpdate.getUserRating());
                    return userProfileRepository.save(userProfile);
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @DeleteMapping(path = "users/{userId}/profile/{profileId}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long userId, @PathVariable Long profileId){
        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException();
        }
        return userProfileRepository.findById(profileId)
                .map(userProfile -> {
                    userProfileRepository.delete(userProfile);
                    return ResponseEntity.ok().build();
                }).orElseThrow(ResourceNotFoundException::new);

    }
}
