package com.company.bookstore.controller;

import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PublisherControllerTest {

    @MockBean
    PublisherRepository publisherRepository;

    @Autowired
    private MockMvc mockMvc;

    private Publisher publisher;

    @BeforeEach
    void setUp(){
        publisherRepository.deleteAll();
        publisher = new Publisher();
        publisher.setName("name");
        publisher.setPhone("000-000-0000");
        publisher.setStreet("123 lane");
        publisher.setCity("city");
        publisher.setState("AL");
        publisher.setPostalCode("00000");
        publisher.setEmail("abc@gmail.com");
        publisherRepository.save(publisher);
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldReturnAllPublishers() throws Exception{
        mockMvc.perform(get("/publishers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();


    }

    @Test
    public void shouldReturnPublisherById() throws Exception{
        mockMvc.perform(get("/publishers/{id}", publisher.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void shouldAddNewPublisher() throws Exception{
        mockMvc.perform(post("/publishers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(publisher))
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

    }

    @Test
    public void shouldUpdatePublisher() throws Exception{
        publisher.setEmail("newemail@gmail.com");
        publisherRepository.save(publisher);
        mockMvc.perform(put("/publishers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(publisher))
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
        ;

    }

    @Test
    public void shouldDeletePublisherById() throws Exception{
        mockMvc.perform(delete("/publishers/{id}", publisher.getId()))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();

    }
}
