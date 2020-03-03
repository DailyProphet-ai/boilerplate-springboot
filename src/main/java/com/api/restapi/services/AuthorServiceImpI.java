package com.api.restapi.services;

import lombok.NoArgsConstructor;
import com.api.restapi.exceptions.ResourceNotFoundException;
import com.api.restapi.models.Author;
import com.api.restapi.models.Book;
import com.api.restapi.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unchecked")
@Service
@NoArgsConstructor
public class AuthorServiceImpI extends IAuthorServiceI {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpI(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Page<Author> getAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author add(Author o) {
        return authorRepository.save(o);
    }

    @Override
    public Author update(Author o, UUID id) {
        Author author = checkIfIdIsPresentandReturnAuthor(id);
        author.setName(o.getName());
        author.setAddress(o.getAddress());
        return authorRepository.save(author);
    }

    @Override
    public Author getById(UUID id) {
        return checkIfIdIsPresentandReturnAuthor(id);
    }

    @Override
    public Author deleteById(UUID id) {
        Author author = checkIfIdIsPresentandReturnAuthor(id);
        authorRepository.deleteById(id);
        return author;
    }

    @Override
    public List<Book> getBooksById(UUID id) {
        return checkIfIdIsPresentandReturnAuthor(id).getBookList();
    }

    private Author checkIfIdIsPresentandReturnAuthor(UUID id) {
        if (!authorRepository.findById(id).isPresent())
            throw new ResourceNotFoundException(" Author id = " + id + " not found");
        else
            return authorRepository.findById(id).get();
    }
}
