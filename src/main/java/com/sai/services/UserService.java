package com.sai.services;

import com.sai.api.dto.UserDto;
import com.sai.api.requests.UserRequest;
import com.sai.model.User;

import java.util.List;

public interface UserService {
    UserDto save(UserRequest user);
    List<User> findAll();
    User findOne(String username);
}
