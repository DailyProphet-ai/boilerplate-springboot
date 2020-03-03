package com.api.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.UUID;

@NoRepositoryBean
public interface ExtendedRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID> {

    T findByDeletedAt(UUID id);

    /*
    T findOne(Long id);
    Iterable<T> findAll();
    Iterable<T> findAll(Sort sort);
    Page<T> findAll(Pageable pageable);*/
}