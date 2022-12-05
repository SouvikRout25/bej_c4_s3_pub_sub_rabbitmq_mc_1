package com.example.UserTrackService.repository;

import com.example.UserTrackService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {

    public User findByUserId(int userId);
}
