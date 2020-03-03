package com.api.restapi.controllers;

import com.api.restapi.models.Book;
import com.api.restapi.results.ResponseWrapper;
import com.api.restapi.services.IBookServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import java.util.UUID;

import static com.api.restapi.constants.ApiConstants.MESSAGE_FOR_REGEX_UUID_MISMATCH;
import static com.api.restapi.constants.ApiConstants.REGEX_FOR_UUID;

@Validated
@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private IBookServiceI IBookService;

    @GetMapping(value = "/{id}")
    public ResponseWrapper<Book> getPublisherById(
            @Valid @Pattern(regexp = REGEX_FOR_UUID, message = MESSAGE_FOR_REGEX_UUID_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(IBookService.getById(UUID.fromString(id)), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseWrapper<Page<Book>> getPublisherAll(Pageable pageable) {
        return new ResponseWrapper<>(IBookService.getAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseWrapper<Book> createPublisher(@Valid @RequestBody Book book) {
        return new ResponseWrapper<>(IBookService.add(book), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseWrapper<Book> deletePublisher(
            @Valid @Pattern(regexp = REGEX_FOR_UUID, message = MESSAGE_FOR_REGEX_UUID_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(IBookService.deleteById(UUID.fromString(id)), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseWrapper<Book> UpdateAuthor(@Valid @RequestBody Book book,
                                              @Valid @Pattern(regexp = REGEX_FOR_UUID, message = MESSAGE_FOR_REGEX_UUID_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(IBookService.update(book, UUID.fromString(id)), HttpStatus.OK);
    }

}
