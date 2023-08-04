package com.company.bookstore.repository;

import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class PublisherRepositoryTests {

    @Autowired
    PublisherRepository publisherRepository;

    Publisher publisher;

    @BeforeEach
    public void setUp() throws Exception{
        publisherRepository.deleteAll();
        publisher = new Publisher();
        publisher.setName("McGraw-Hill");
        publisher.setStreet("Hollywood");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("11100");
        publisher.setPhone("111-222-3333");
        publisher.setEmail("mcGrawHill@gmail.com");
        publisherRepository.save(publisher);
    }

    @Test
    public void shouldAddPublisher(){

        //Arrange...
        Publisher publisher1 = publisher;


        publisherRepository.save(publisher1);

        //Act...
        Optional<Publisher> publisher2 = publisherRepository.findById(publisher1.getId());

        //Assert...
        assertEquals(publisher2.get(), publisher1);

    }

    @Test
    public void shouldGetPublisherById(){

        //Arrange...
        publisherRepository.save(publisher);

        //Act...
        Optional<Publisher> foundPublisher = publisherRepository.findById(publisher.getId());

        //Assert...
        assertEquals(foundPublisher.get(), publisher);
    }

    @Test
    public void shouldGetAllPublishers(){

        //Arrange...
        publisherRepository.save(publisher);

        //Act...
        List<Publisher> plist = publisherRepository.findAll();

        //Assert
        assertEquals(plist.size(), 1);

    }

    @Test
    public void shouldUpdatePublisher(){

        //Arrange...
        publisherRepository.save(publisher);
        publisher.setCity("Palm Springs");

        //Act...
        Optional<Publisher> publisher1 = publisherRepository.findById(publisher.getId());

        //Assert...
        assertEquals(publisher1.get(), publisher);

    }

    @Test
    public void shouldDeletePublisherById(){

        //Arrange...
        publisherRepository.save(publisher);

        //Act...
        publisherRepository.deleteById(publisher.getId());
        Optional<Publisher> publisher1 = publisherRepository.findById(publisher.getId());

        //Assert...
        assertFalse(publisher1.isPresent());
    }
    }
