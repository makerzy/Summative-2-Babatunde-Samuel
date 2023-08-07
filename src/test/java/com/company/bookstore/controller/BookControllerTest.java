package com.company.bookstore.controller;

import com.company.bookstore.controllers.BookController;
import com.company.bookstore.models.Book;
import com.company.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    @MockBean
    private BookRepository bookRepository;
    private Book book;

    @BeforeEach
    public void setup() {
        book = new Book();
        book.setIsbn("9783161484100");
        book.setPublishDate("2015-08-01");
        book.setTitle("An interesting book");
        book.setPrice(29.99);
    }

    @Test
    public void addBookTest() throws Exception {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/book")
                        .content(mapper.writeValueAsString(book))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isbn").value("9783161484100"))
                .andExpect(jsonPath("$.publishDate").value("2015-08-01"))
                .andExpect(jsonPath("$.title").value("An interesting book"))
                .andExpect(jsonPath("$.price").value(29.99));
    }

    @Test
    public void getBookByIdTest() throws Exception {
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/{bookId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value("9783161484100"))
                .andExpect(jsonPath("$.publishDate").value("2015-08-01"))
                .andExpect(jsonPath("$.title").value("An interesting book"))
                .andExpect(jsonPath("$.price").value(29.99));
    }

    @Test
    public void getAllBooksTest() throws Exception {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn").value("9783161484100"))
                .andExpect(jsonPath("$[0].publishDate").value("2015-08-01"))
                .andExpect(jsonPath("$[0].title").value("An interesting book"))
                .andExpect(jsonPath("$[0].price").value(29.99));
    }

    @Test
    public void updateBookTest() throws Exception {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/books/{bookId}", book.getBookId())
                        .content(mapper.writeValueAsString(book))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/books/{bookId}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getBooksByAuthorIdTest() throws Exception {
        when(bookRepository.findByAuthorId(1)).thenReturn(Arrays.asList(book));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/authors/{authorId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn").value("9783161484100"))
                .andExpect(jsonPath("$[0].publishDate").value("2015-08-01"))
                .andExpect(jsonPath("$[0].title").value("An interesting book"))
                .andExpect(jsonPath("$[0].price").value(29.99));
    }
}
