package com.api.restapi.repositories;

import com.api.restapi.models.Book;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends ExtendedRepository<Book, UUID> {
}
