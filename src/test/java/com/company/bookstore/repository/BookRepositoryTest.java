package com.company.bookstore.repository;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;

    private Book book;
    private Author author;
    private Publisher publisher;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        publisherRepository.deleteAll();

        book = new Book();
        author = new Author();
        publisher = new Publisher();

        author.setFirstName("Stephen");
        author.setLastName("King");
        author.setStreet("Hollywood");
        author.setCity("Los Angeles");
        author.setState("CA");
        author.setPostalCode("11100");
        author.setPhone("111-222-3333");
        author.setEmail("sking@gmail.com");
        authorRepository.save(author);

        publisher.setName("McGraw-Hill");
        publisher.setStreet("Hollywood");
        publisher.setCity("Los Angeles");
        publisher.setState("CA");
        publisher.setPostalCode("11100");
        publisher.setPhone("111-222-3333");
        publisher.setEmail("mcGrawHill@gmail.com");
        publisherRepository.save(publisher);

        book.setIsbn("9783161484100");
        book.setPublishDate("2015-08-01");
        book.setAuthor(author);
        book.setTitle("An interesting book");
        book.setPublisher(publisher);
        book.setPrice(29.99);
        book = bookRepository.save(book);
    }

    @Test
    public void addBook() {
        Optional<Book> newBook = bookRepository.findById(book.getBookId());
        assertEquals(newBook.get(), book);
    }

    @Test
    public void updateBook() {
        book.setTitle("book title changed");
        bookRepository.save(book);

        Optional<Book> updatedBook = bookRepository.findById(book.getBookId());
        assertEquals(updatedBook.get(), book);
    }

    @Test
    public void deleteBook() {
        bookRepository.deleteById(book.getBookId());

        Optional<Book> deletedBook = bookRepository.findById(book.getBookId());
        assertFalse(deletedBook.isPresent());
    }

    @Test
    public void getBookById() {
        Book foundBook = bookRepository.findById(book.getBookId()).orElse(null);
        assertEquals(book, foundBook);
    }

    @Test
    public void getBooksByAuthorId() {
        Book newBook = new Book();
        Author newAuthor = new Author();
        Publisher newPublisher = new Publisher();

        newAuthor.setFirstName("new author first name");
        newAuthor.setLastName("new author last name");
        newAuthor.setStreet("new author street address");
        newAuthor.setCity("new author city");
        newAuthor.setState("new author state");
        newAuthor.setPostalCode("6789");
        newAuthor.setPhone("222-222-222");
        newAuthor.setEmail("newAuthor@gmail.com");
        authorRepository.save(newAuthor);

        newPublisher.setName("new publisher name");
        newPublisher.setStreet("new publisher street address");
        newPublisher.setCity("new publisher city");
        newPublisher.setState("new publisher state");
        newPublisher.setPostalCode("65435");
        newPublisher.setPhone("666-666-666");
        newPublisher.setEmail("newPublisher@gmail.com");
        publisherRepository.save(newPublisher);

        newBook.setIsbn("newIsbn");
        newBook.setPublishDate("02/02/02");
        newBook.setAuthor(newAuthor);
        newBook.setTitle("newBook title");
        newBook.setPublisher(newPublisher);
        newBook.setPrice(1.00);
        bookRepository.save(newBook);

        List<Book> books = bookRepository.getBooksByAuthorAuthorId(book.getAuthor().getAuthorId());
        assertTrue(books.contains(book));
        assertFalse(books.contains(newBook));
    }
}
