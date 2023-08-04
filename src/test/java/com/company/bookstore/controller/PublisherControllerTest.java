package com.company.bookstore.controller;

import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublisherControllerTest.class)
public class PublisherControllerTest {

    @MockBean
    PublisherRepository publisherRepository;

    @Autowired
    private MockMvc mockMvc;

    private Publisher publisher;

    @BeforeEach
    void setUp(){
        publisher = new Publisher();
        publisher.setName("name");
        publisher.setPhone("000-000-0000");
        publisher.setStreet("123 lane");
        publisher.setCity("city");
        publisher.setState("AL");
        publisher.setPostalCode("00000");
        publisher.setEmail("abc@gmail.com");
    }

    @Test
    public void shouldReturnAllPublishers() throws Exception{

        publisherRepository.save(publisher);
        mockMvc.perform(get("/publishers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.getName()").value("name"))
                .andExpect(jsonPath("$.getPhone()").value("000-000-0000"))
                .andExpect(jsonPath("$.getStreet()").value("123 lane"))
                .andExpect(jsonPath("$.getCity()").value("city"))
                .andExpect(jsonPath("$.getState()").value("AL"))
                .andExpect(jsonPath("$.getPostalCode()").value("00000"))
                .andExpect(jsonPath("$.getEmail()").value("abc@gmail.com"));

    }
}
