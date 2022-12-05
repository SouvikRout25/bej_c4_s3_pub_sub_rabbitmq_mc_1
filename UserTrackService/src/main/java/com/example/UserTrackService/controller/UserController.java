package com.example.UserTrackService.controller;


import com.example.UserTrackService.exception.TrackNotFoundException;
import com.example.UserTrackService.exception.UserAlreadyExistsException;
import com.example.UserTrackService.exception.UserNotFoundException;
import com.example.UserTrackService.model.Track;
import com.example.UserTrackService.model.User;
import com.example.UserTrackService.rabbitmq.CommonUser;
import com.example.UserTrackService.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("usertrack/app")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> insertUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            user.setTrackList(new ArrayList<>());
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        }catch (UserAlreadyExistsException ex){
            throw new UserAlreadyExistsException();
        }
    }

    @PutMapping("addtrack/{userId}")
    public ResponseEntity<?> addTrack(@PathVariable int userId, @RequestBody Track track) throws UserNotFoundException {
        try {
            return new ResponseEntity<>(userService.addTrackToPlaylist(userId,track),HttpStatus.CREATED);
        }catch (UserNotFoundException ex){
            throw new UserNotFoundException();
        }
    }

    @DeleteMapping("deletetrack/{userId}/{trackId}")
    public ResponseEntity<?> deleteTrack(@PathVariable int userId, @PathVariable int trackId) throws UserNotFoundException, TrackNotFoundException {
        try {
            return new ResponseEntity<>(userService.deleteTrackFromPlaylist(userId,trackId),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (TrackNotFoundException e) {
            throw new TrackNotFoundException();
        }
    }

    @GetMapping("gettrack/{userId}")
    public ResponseEntity<?> getTrack(@PathVariable int userId) throws UserNotFoundException {
        try {
            return new ResponseEntity<>(userService.getAllTrackFromPlaylist(userId),HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
    }

    @PutMapping("updatetrack/{userId}")
    public ResponseEntity<?> updateTrack(@PathVariable int userId, @RequestBody Track track) throws UserNotFoundException {

        try{
            return new ResponseEntity<>(userService.updateTrackInPlaylist(userId,track),HttpStatus.OK);
        }catch (UserNotFoundException ex){
            throw new UserNotFoundException();
        }
    }

    @PostMapping("/common")
    public ResponseEntity<?> addUserDetails(@RequestBody CommonUser commonUser){

        return new ResponseEntity<>(userService.addUser1(commonUser),HttpStatus.OK);
    }

}