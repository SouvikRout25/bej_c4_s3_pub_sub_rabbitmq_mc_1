package com.example.User_Authentication.services;


import com.example.User_Authentication.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<Integer, String> generateToken(User user);
}