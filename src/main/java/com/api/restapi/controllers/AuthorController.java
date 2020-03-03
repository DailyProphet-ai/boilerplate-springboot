package com.api.restapi.controllers;

import com.api.restapi.models.Author;
import com.api.restapi.models.Book;
import com.api.restapi.results.ResponseWrapper;
import com.api.restapi.services.IAuthorServiceI;
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
@RequestMapping("/api/v1/authors")
public class AuthorController {

    @Autowired
    private IAuthorServiceI authorMainService;

    @GetMapping(value = "/{id}")
    public ResponseWrapper<Author> getAuthorById(
            @Valid @Pattern(regexp = REGEX_FOR_UUID, message = MESSAGE_FOR_REGEX_UUID_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(authorMainService.getById(UUID.fromString(id)), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseWrapper<Page<Author>> getAuthorAll(Pageable pageable) {
        return new ResponseWrapper<>(authorMainService.getAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseWrapper<Author> createAuthor(@Valid @RequestBody Author author) {
        return new ResponseWrapper<>(authorMainService.add(author), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseWrapper<Author> deleteAuthor(
            @Valid @Pattern(regexp = REGEX_FOR_UUID, message = MESSAGE_FOR_REGEX_UUID_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(authorMainService.deleteById(UUID.fromString(id)), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseWrapper<Author> UpdateAuthor(@Valid @RequestBody Author author,
                                                @Valid @Pattern(regexp = REGEX_FOR_UUID, message = MESSAGE_FOR_REGEX_UUID_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(authorMainService.update(author, UUID.fromString(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/books")
    public ResponseWrapper<List<Book>> getAuthorBooksById(
            @Valid @Pattern(regexp = REGEX_FOR_UUID, message = MESSAGE_FOR_REGEX_UUID_MISMATCH) @PathVariable(value = "id") String id) {
        return new ResponseWrapper<>(authorMainService.getBooksById(UUID.fromString(id)), HttpStatus.OK);
    }

}
