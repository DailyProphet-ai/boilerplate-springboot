package com.api.restapi.services;

import com.api.restapi.models.Author;
import com.api.restapi.models.Book;

import java.util.List;
import java.util.UUID;

public abstract class IAuthorServiceI implements IMainService<Author> {
    public abstract List<Book> getBooksById(UUID id);
}
