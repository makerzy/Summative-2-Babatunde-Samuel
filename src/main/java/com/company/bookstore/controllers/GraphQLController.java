package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.service.AuthorServiceLayer;
import com.company.bookstore.viewmodel.AuthorViewModel;
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

    @QueryMapping
    public Author findAuthorById(@Argument String id){
        Optional<Author> author = authorRepository.findById(Integer.parseInt(id));
        return author.orElse(null);

    }

    @SchemaMapping
    public List<Book> books(Author author) {
        return bookRepository.findByAuthorId(author.getAuthorId());
    }

    @QueryMapping
    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }
}
