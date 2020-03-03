package com.api.restapi.repositories;

import com.api.restapi.models.Author;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepository extends ExtendedRepository<Author, UUID> {

}
