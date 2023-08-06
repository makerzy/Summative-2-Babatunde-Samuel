package com.company.bookstore.controllers;


import com.company.bookstore.models.Author;
import com.company.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@RequestBody Author author) {

        return authorRepository.save(author);
    }

    @GetMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getAuthorById(@PathVariable int id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.orElse(null);
    }

    @GetMapping("/authors")
    @ResponseStatus(HttpStatus.OK)
    public List<Author> getAuthors() {

        return authorRepository.findAll();
    }

    @PutMapping(value = "/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthor(@RequestBody Author author, @PathVariable int id) {
        Optional<Author> author1 = authorRepository.findById(id);
        if (author1.isPresent()) // only update if there is an existing row with the author Id
            authorRepository.save(author);
//        else{
//            throw new RuntimeException("No existing author with id "+ id);
//        }
    }

    @DeleteMapping(value = "/authors/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable int id) {
        authorRepository.deleteById(id);
    }

}
