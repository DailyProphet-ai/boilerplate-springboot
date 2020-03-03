package com.api.restapi.repositories;

import com.api.restapi.models.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends ExtendedRepository<User, UUID> {

    @Query("select u from User u where u.email=?1 and u.password=?2")
    Optional<User> login(String email, String password);

    Optional<User> findByEmail(String email);
}