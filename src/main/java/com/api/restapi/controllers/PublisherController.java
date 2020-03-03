package com.api.restapi.controllers;

import com.api.restapi.exceptions.ResourceNotFoundException;
import com.api.restapi.models.Book;
import com.api.restapi.models.Publisher;
import com.api.restapi.results.ResponseWrapper;
import com.api.restapi.services.IPublisherServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

import static com.api.restapi.constants.ApiConstants.MESSAGE_FOR_REGEX_UUID_MISMATCH;
import static com.api.restapi.constants.ApiConstants.REGEX_FOR_UUID;

@Validated
@RestController
@RequestMapping("/api/v1/publishers")
public class PublisherController {

    @Autowired
    private IPublisherServiceI IPublisherService;

    @GetMapping(value = "/{id}")
    public ResponseWrapper<Publisher> getPublisherById(
            @Valid @Pattern(regexp = REGEX_FOR_UUID, message = MESSAGE_FOR_REGEX_UUID_MISMATCH) @PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        return new ResponseWrapper<>(IPublisherService.getById(UUID.fromString(id)), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseWrapper<Page<Publisher>> getPublisherAll(Pageable pageable) {
        return new ResponseWrapper<>(IPublisherService.getAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseWrapper<Publisher> createPublisher(@Valid @RequestBody Publisher publisher)
            throws ResourceNotFoundException {
        return new ResponseWrapper<>(IPublisherService.add(publisher), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseWrapper<Publisher> deletePublisher(
            @Valid @Pattern(regexp = REGEX_FOR_UUID, message = MESSAGE_FOR_REGEX_UUID_MISMATCH) @PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        return new ResponseWrapper<>(IPublisherService.deleteById(UUID.fromString(id)), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseWrapper<Publisher> UpdateAuthor(@Valid @RequestBody Publisher publisher,
                                                   @Valid @Pattern(regexp = REGEX_FOR_UUID, message = MESSAGE_FOR_REGEX_UUID_MISMATCH) @PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        return new ResponseWrapper<>(IPublisherService.update(publisher, UUID.fromString(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/books")
    public ResponseWrapper<List<Book>> getPublisherBooksById(
            @Valid @Pattern(regexp = REGEX_FOR_UUID, message = MESSAGE_FOR_REGEX_UUID_MISMATCH) @PathVariable(value = "id") String id)
            throws ResourceNotFoundException {
        return new ResponseWrapper<>(IPublisherService.getBooksById(UUID.fromString(id)), HttpStatus.OK);
    }

}
