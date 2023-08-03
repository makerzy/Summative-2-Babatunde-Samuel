package com.company.bookstore.repository;

import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PublisherRepositoryTests {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PublisherRepository publisherRepository;

    @BeforeEach
    public void setUp() throws Exception{
        authorRepository.deleteAll();
        bookRepository.deleteAll();
        publisherRepository.deleteAll();
    }

    @Test
    public void shouldAddPublisher(){

        //Arrange...
        Publisher publisher = new Publisher();
        publisher.setName("McGraw-Hill");
        publisher.setStreet("Hollywood");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("11100");
        publisher.setPhone("111-222-3333");
        publisher.setEmail("mcGrawHill@gmail.com");

        publisherRepository.save(publisher);

        //Act...
        Optional<Publisher> publisher1 = publisherRepository.findById(publisher.getId());

        //Assert...
        assertEquals(publisher1.get(), publisher);

    }

    @Test
    public void shouldGetPublisherById(){

        //Arrange...
        Publisher publisher = new Publisher();
        publisher.setName("McGraw-Hill");
        publisher.setStreet("Hollywood");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("11100");
        publisher.setPhone("111-222-3333");
        publisher.setEmail("mcGrawHill@gmail.com");
        publisherRepository.save(publisher);

        Publisher publisher2 = new Publisher();
        publisher2.setName("Scholastic");
        publisher2.setStreet("Broadway");
        publisher2.setCity("New York");
        publisher2.setState("NY");
        publisher2.setPostalCode("10012");
        publisher2.setPhone("000-111-2222");
        publisher2.setEmail("scholastic@gmail.com");
        publisherRepository.save(publisher2);

        //Act...
        Optional<Publisher> foundPublisher = publisherRepository.findById(publisher.getId());

        //Assert...
        assertEquals(foundPublisher.get(), publisher);
    }

    @Test
    public void shouldGetAllPublishers(){

    }

    @Test
    public void shouldUpdatePublisher(){

    }

    @Test
    public void shouldDeletePublisherById(){

    }
    }
