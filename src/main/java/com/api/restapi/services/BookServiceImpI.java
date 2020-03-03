package com.api.restapi.services;

import com.api.restapi.exceptions.ResourceNotFoundException;
import com.api.restapi.models.Author;
import com.api.restapi.models.Book;
import com.api.restapi.models.Publisher;
import com.api.restapi.repositories.AuthorRepository;
import com.api.restapi.repositories.BookRepository;
import com.api.restapi.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookServiceImpI extends IBookServiceI {


    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpI(BookRepository bookRepository, PublisherRepository publisherRepository,
                           AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Page<Book> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book add(Book o) {
        //if author n publisher gets an id we can search the db and assign them
        if (o.getAuthor().getId() != null) {
            Author author = checkIfIdIsPresentandReturnAuthor(o.getAuthor().getId());
            o.setAuthor(author);
            author.addBook(o);
        }
        if (o.getPublisher().getId() != null) {
            Publisher publisher = checkIfIdIsPresentandReturnPublisher(o.getPublisher().getId());
            o.setPublisher(publisher);
            publisher.addBook(o);
        }

        return bookRepository.save(o);
    }

    @Override
    public Book update(Book o, UUID id) throws ResourceNotFoundException {

        Book oldBook = checkIfIdIsPresentandReturnBook(id);
        if (o.getName() != null)
            oldBook.setName(o.getName());
        if (o.getAuthor() != null) {
            Author author;
            if (o.getAuthor().getId() != null) {
                //get the author by Id
                author = checkIfIdIsPresentandReturnAuthor(o.getAuthor().getId());
                author.addBook(oldBook);
            } else
                author = o.getAuthor();

            oldBook.setAuthor(author);

        }
        if (o.getPublisher() != null) {
            Publisher publisher;
            if (o.getPublisher().getId() != null) {
                publisher = checkIfIdIsPresentandReturnPublisher(o.getPublisher().getId());
                publisher.addBook(oldBook);
            } else
                publisher = o.getPublisher();
            oldBook.setPublisher(publisher);
        }
        if (o.getPrice() != 0)
            oldBook.setPrice(o.getPrice());
        return bookRepository.save(oldBook);
    }

    @Override
    public Book getById(UUID id) throws ResourceNotFoundException {
        return checkIfIdIsPresentandReturnBook(id);
    }

    @Override
    public Book deleteById(UUID id) throws ResourceNotFoundException {
        Book book = checkIfIdIsPresentandReturnBook(id);
        bookRepository.deleteById(id);
        return book;
    }

    private Book checkIfIdIsPresentandReturnBook(UUID id) {
        if (!bookRepository.findById(id).isPresent())
            throw new ResourceNotFoundException(" Book id=" + id + " not found");
        else
            return bookRepository.findById(id).get();
    }

    private Author checkIfIdIsPresentandReturnAuthor(UUID id) {
        if (!authorRepository.findById(id).isPresent())
            throw new ResourceNotFoundException(" Author id = " + id + " not found");
        else
            return authorRepository.findById(id).get();
    }

    private Publisher checkIfIdIsPresentandReturnPublisher(UUID id) {
        if (!publisherRepository.findById(id).isPresent())
            throw new ResourceNotFoundException(" Publisher id = " + id + " not found");
        else
            return publisherRepository.findById(id).get();
    }
}
