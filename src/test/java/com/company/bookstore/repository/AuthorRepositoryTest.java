package com.company.bookstore.repository;


import com.company.bookstore.models.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;
    Author author;

    @BeforeEach
    public void setUp(){
        authorRepository.deleteAll();
        author = new Author();
        author.setFirstName("first_name");
        author.setLastName("last_name");
        author.setEmail("email@email.com");
        author.setPhone("888-888-888");
        author.setStreet("1234 street");
        author.setCity("city");
        author.setState("state");
        author.setPostalCode("12345");
        authorRepository.save(author);
    }

    @Test
    public void shouldSaveNewAuthor(){
        Author author1 = author;
        author1.setAuthorId(author.getAuthorId()+1);
        authorRepository.save(author1);
        Optional<Author> author2 = authorRepository.findById(author1.getAuthorId());
        assertEquals(author2.get(), author1);
    }

    @Test
    public void shouldGetAuthorById() {
        authorRepository.save(author);
        Optional<Author> author1 = authorRepository.findById(author.getAuthorId());
        assertEquals(author1.get(), author);
    }

    @Test
    public void shouldGetAllAuthors(){
        authorRepository.save(author);
        List<Author> authors = authorRepository.findAll();
        assertEquals(authors.size(), 1);
    }

    @Test
    public  void  shouldUpdateAuthor(){
        authorRepository.save(author);
        author.setState("California");
        authorRepository.save(author);
        Optional<Author> author1 = authorRepository.findById(author.getAuthorId());
        assertEquals(author1.get().getState(), "California");
        assertEquals(author1.get(), author);
    }

    @Test
    public void shouldDeleteAuthorById(){
        authorRepository.save(author);
        authorRepository.deleteById(author.getAuthorId());
        Optional<Author> author1 = authorRepository.findById(author.getAuthorId());
        assertFalse(author1.isPresent());
    }
}
