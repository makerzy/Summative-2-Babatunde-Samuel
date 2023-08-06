package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import com.company.bookstore.service.AuthorServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphQLController {
    @Autowired
    AuthorServiceLayer authorServiceLayer;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;
    @QueryMapping
    public Author findAuthorById(@Argument String id){
        Optional<Author> author = authorRepository.findById(Integer.parseInt(id));
        return author.orElse(null);
    }

    @QueryMapping
    public Publisher findPublisherById(@Argument String id){
        Optional<Publisher> publisher = publisherRepository.findById(Integer.parseInt(id));
        return publisher.orElse(null);
    }

    @QueryMapping
    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    @QueryMapping
    public List<Publisher> getPublishers(){return publisherRepository.findAll();}



    @SchemaMapping
    public List<Book> books(Author author) {
        return bookRepository.findByAuthorId(author.getAuthorId());
    }

    @SchemaMapping
    public Author author(Book book){
        Optional<Author> author = authorRepository.findById(book.getAuthorId());
        return author.orElse(null);
    }

    @SchemaMapping
    public Publisher publisher(Book book){
        Optional<Publisher> publisher = publisherRepository.findById(book.getPublisherId());
        return publisher.orElse(null);
    }


}
