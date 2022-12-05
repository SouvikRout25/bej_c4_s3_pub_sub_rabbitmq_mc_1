package com.example.UserTrackService.services;

import com.example.UserTrackService.exception.TrackNotFoundException;
import com.example.UserTrackService.exception.UserAlreadyExistsException;
import com.example.UserTrackService.exception.UserNotFoundException;
import com.example.UserTrackService.model.Track;
import com.example.UserTrackService.model.User;
import com.example.UserTrackService.rabbitmq.CommonUser;

import java.util.List;

public interface UserService {

    public abstract User addUser(User user) throws UserAlreadyExistsException;

    public abstract User addTrackToPlaylist(int userId, Track track) throws UserNotFoundException;

    public abstract User deleteTrackFromPlaylist(int userId, int trackId) throws UserNotFoundException, TrackNotFoundException;

    public abstract List<Track> getAllTrackFromPlaylist(int userId) throws UserNotFoundException;

    public abstract User updateTrackInPlaylist(int userId, Track track) throws  UserNotFoundException;

    User addUser1(CommonUser commonUser);
}
