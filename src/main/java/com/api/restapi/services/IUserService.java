package com.api.restapi.services;

import com.api.restapi.models.User;

import java.util.UUID;

public interface IUserService extends IMainService<User> {

    User login(String email, String password);

    User createUser(User user);

    User updateUser(User user);

    void deleteUserById(UUID id);
}
