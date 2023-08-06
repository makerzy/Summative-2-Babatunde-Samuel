package com.company.bookstore.controller;


import com.company.bookstore.models.Author;
import com.company.bookstore.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
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
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldGetAllAuthors() throws Exception {
        mockMvc.perform(get("/authors")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }

    @Test
    public void shouldGetAuthorById() throws Exception {
        mockMvc.perform(get("/authors/{id}", author.getAuthorId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldAddNewAuthor() throws Exception {
        Author author1 = new Author();
        author1.setFirstName("Firstname1");
        author1.setLastName("Lastname1");
        author1.setEmail("firstname1.lastname1@author.com");
        author1.setPhone("800-800-8001");
        author1.setStreet("33 address 2");
        author1.setCity("San Jose");
        author1.setState("California");
        author1.setPostalCode("49300");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/authors")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(author1))
                                .characterEncoding("utf-8")
                )

                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();


    }
    @Test
    public void shouldUpdateAuthorById() throws Exception {
        author.setFirstName("new_first_name");
        author.setLastName("new_last_name");
        mockMvc.perform(put("/authors/{id}", author.getAuthorId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author))
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void shouldDeleteAuthorById() throws Exception {
        int id = author.getAuthorId();
        mockMvc.perform(delete("/authors/{id}", id)
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }

}
