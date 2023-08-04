package com.company.bookstore.service;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.viewmodel.AuthorViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class AuthorServiceTest {

    AuthorServiceLayer authorServiceLayer;

    AuthorRepository authorRepository;
    BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        setUpAuthorRepositoryMock();
        setUpBookRepositoryMock();
        authorServiceLayer = new AuthorServiceLayer(authorRepository, bookRepository);
    }

    private void setUpAuthorRepositoryMock() {
        authorRepository = mock(AuthorRepository.class);
        Author author = new Author();
        author.setAuthorId(1);
        author.setFirstName("first_name");
        author.setLastName("last_name");
        author.setEmail("email@email.com");
        author.setPhone("888-888-888");
        author.setStreet("1234 street");
        author.setCity("city");
        author.setState("state");
        author.setPostalCode("12345");

        Author author2 = new Author();
        author2.setAuthorId(2);
        author2.setFirstName("first_name_1");
        author2.setLastName("last_name_1");
        author2.setEmail("email@email_1.com");
        author2.setPhone("1-888-888-888");
        author2.setStreet("1234 street-1");
        author2.setCity("city1");
        author2.setState("state1");
        author2.setPostalCode("12340");

        List<Author> aList = new ArrayList<>();
        aList.add(author);
        aList.add(author2);

        doReturn(author).when(authorRepository).save(author);
        doReturn(author2).when(authorRepository).save(author2);
        doReturn(Optional.of(author)).when(authorRepository).findById(1);
        doReturn(Optional.of(author2)).when(authorRepository).findById(2);
        doReturn(aList).when(authorRepository).findAll();
    }


    private void setUpBookRepositoryMock() {
        bookRepository = mock(BookRepository.class);

        Book book = new Book();
        book.setBookId(1);
        book.setAuthorId(1);

        Book book2 = new Book();
        book2.setBookId(1);
        book2.setAuthorId(2);

        List<Book> bookList = new ArrayList<>();
        List<Book> bookList2 = new ArrayList<>();
        bookList.add(book);
        bookList2.add(book2);


        doReturn(book).when(bookRepository).save(book);
        doReturn(book2).when(bookRepository).save(book2);
        doReturn(bookList).when(bookRepository).findByAuthorId(1);
        doReturn(bookList2).when(bookRepository).findByAuthorId(2);

    }

    @Test
    public void shouldGetAuthors() {
        List<AuthorViewModel> authors = authorServiceLayer.getAuthors();
        System.out.println(authors.toString());
        assertEquals(authors.size(), 2);
    }

    @Test
    public void shouldGetAuthorById() {
        Book book = new Book();
        book.setBookId(1);
        book.setAuthorId(1);

        List<Book> bookList = new ArrayList<>();

        bookList.add(book);
        AuthorViewModel authorView = new AuthorViewModel();
        authorView.setAuthorId(1);
        authorView.setFirstName("first_name");
        authorView.setLastName("last_name");
        authorView.setEmail("email@email.com");
        authorView.setPhone("888-888-888");
        authorView.setStreet("1234 street");
        authorView.setCity("city");
        authorView.setState("state");
        authorView.setPostalCode("12345");
        authorView.setBooks(bookList);

        AuthorViewModel authorView1 = authorServiceLayer.getAuthorById(1);
        assertEquals(authorView1.getBooks().get(0).getAuthorId(),
                authorView.getBooks().get(0).getAuthorId());
        assertEquals(authorView1.getBooks().get(0).getBookId(),
                authorView.getBooks().get(0).getBookId());
    }

}
