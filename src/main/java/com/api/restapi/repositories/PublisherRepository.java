package com.api.restapi.repositories;

import com.api.restapi.models.Publisher;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublisherRepository extends ExtendedRepository<Publisher, UUID> {
}
