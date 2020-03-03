package com.api.restapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IMainService<T> {
    Page<T> getAll(Pageable pageable);

    T add(T o);

    T update(T o, UUID id);

    T getById(UUID id);

    T deleteById(UUID id);
}

