package com.api.restapi.services;

import com.api.restapi.exceptions.ResourceNotFoundException;
import com.api.restapi.repositories.UserRepository;
import com.api.restapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ApiUtilsImpl apiUtils;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ApiUtilsImpl apiUtils, PasswordEncoder passwordEncoder) {
        Assert.notNull(userRepository, "UserRepository must not be null!");
        Assert.notNull(apiUtils, "ApiUtils must not be null!");
        this.userRepository = userRepository;
        this.apiUtils = apiUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User login(String email, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Optional<User> user = userRepository.login(email, encodedPassword);
        if (!user.isPresent())
            throw new ResourceNotFoundException("Login failed");

        return user.get();
    }

    @Override
    public User createUser(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new ResourceNotFoundException("User with email " + user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User add(User o) {
        return null;
    }

    @Override
    public User update(User user, UUID id) {
        User user_ = checkAndGetUSer(id);
        apiUtils.merge(user_, user);
        return userRepository.save(user_);
    }

    @Override
    public User getById(UUID id) {
        return checkAndGetUSer(id);
    }

    @Override
    public User deleteById(UUID id) {
        User user = checkAndGetUSer(id);
        userRepository.deleteById(id);
        return user;
    }


    private User checkAndGetUSer(UUID id) {
        if (!userRepository.findById(id).isPresent())
            throw new ResourceNotFoundException(" User id = " + id + " not found");
        else
            return userRepository.findById(id).get();
    }
}