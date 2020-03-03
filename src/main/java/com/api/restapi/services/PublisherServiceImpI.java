package com.api.restapi.services;

import lombok.NoArgsConstructor;
import com.api.restapi.exceptions.ResourceNotFoundException;
import com.api.restapi.models.Book;
import com.api.restapi.models.Publisher;
import com.api.restapi.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unchecked")
@Service
@NoArgsConstructor
public class PublisherServiceImpI extends IPublisherServiceI {

    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpI(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Page<Publisher> getAll(Pageable pageable) {
        return publisherRepository.findAll(pageable);
    }

    @Override
    public Publisher add(Publisher o) {
        return publisherRepository.save(o);
    }

    @Override
    public Publisher update(Publisher o, UUID id) {
        Publisher publisher = checkIfIdIsPresentandReturnPublisher(id);
        publisher.setName(o.getName());
        publisher.setAddress(o.getAddress());
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher getById(UUID id) throws ResourceNotFoundException {
        return checkIfIdIsPresentandReturnPublisher(id);
    }

    @Override
    public Publisher deleteById(UUID id) {
        Publisher publisher = checkIfIdIsPresentandReturnPublisher(id);
        publisherRepository.deleteById(id);
        return publisher;
    }

    @Override
    public List<Book> getBooksById(UUID id) {
        return checkIfIdIsPresentandReturnPublisher(id).getBookList();
    }

    private Publisher checkIfIdIsPresentandReturnPublisher(UUID id) {
        if (!publisherRepository.findById(id).isPresent())
            throw new ResourceNotFoundException(" Publisher id = " + id + " not found");
        else
            return publisherRepository.findById(id).get();
    }
}
