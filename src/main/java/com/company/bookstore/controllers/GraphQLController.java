package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
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
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;
    @QueryMapping
    public Author findAuthorById(@Argument int id){
        Optional<Author> author = authorRepository.findById(id);
        return author.orElse(null);
    }

    @QueryMapping
    public Publisher findPublisherById(@Argument int id){
        Optional<Publisher> publisher = publisherRepository.findById(id);
        return publisher.orElse(null);
    }

    @QueryMapping
    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    @QueryMapping
    public List<Publisher> getPublishers(){return publisherRepository.findAll();}

    @QueryMapping
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    @SchemaMapping
    public List<Book> books(Author author) {
        return bookRepository.findByPublisherId(author.getAuthorId());
    }

    @SchemaMapping
    public List<Book> books(Publisher publisher){
        return bookRepository.findByPublisherId(publisher.getPublisherId());
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
