package com.api.restapi.services;

import com.api.restapi.models.Book;
import com.api.restapi.models.Publisher;

import java.util.List;
import java.util.UUID;

public abstract class IPublisherServiceI implements IMainService<Publisher> {
    public abstract List<Book> getBooksById(UUID id);
}
