package com.api.restapi.controllers;

import com.api.restapi.exceptions.ResourceNotFoundException;
import com.api.restapi.results.ResponseWrapper;
import com.api.restapi.services.IUserService;
import com.api.restapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.UUID;

import static com.api.restapi.constants.ApiConstants.MESSAGE_FOR_REGEX_UUID_MISMATCH;
import static com.api.restapi.constants.ApiConstants.REGEX_FOR_UUID;


@Validated
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseWrapper<User> createUser(@Valid @RequestBody User user)
            throws ResourceNotFoundException {
        return new ResponseWrapper<>(userService.createUser(user));
    }

    @PostMapping(value = "/login")
    public ResponseWrapper<User> login(@Valid @RequestBody User user)
            throws ResourceNotFoundException {
        return new ResponseWrapper<>(userService.login(user.getEmail(), user.getPassword()));
    }

    @GetMapping(value = "/{id}")
    public ResponseWrapper<User> getUserById(
            @Valid @Pattern(regexp = REGEX_FOR_UUID, message = MESSAGE_FOR_REGEX_UUID_MISMATCH) @PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        return new ResponseWrapper<>(userService.getById(UUID.fromString(id)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseWrapper<User> deleteUser(
            @Valid @Pattern(regexp = REGEX_FOR_UUID, message = MESSAGE_FOR_REGEX_UUID_MISMATCH) @PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        return new ResponseWrapper<>(userService.deleteById(UUID.fromString(id)), HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}")
    public ResponseWrapper<User> UpdateAuthor(@Valid @RequestBody User user,
                                              @Valid @Pattern(regexp = REGEX_FOR_UUID, message = MESSAGE_FOR_REGEX_UUID_MISMATCH) @PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        return new ResponseWrapper<>(userService.update(user, UUID.fromString(id)));
    }

    @GetMapping(value = "/")
    public ResponseWrapper<Page<User>> getAllUsers(Pageable pageable) {
        return new ResponseWrapper<>(userService.getAll(pageable));
    }
}