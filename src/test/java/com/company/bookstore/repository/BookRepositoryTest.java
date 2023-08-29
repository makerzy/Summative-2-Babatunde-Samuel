package com.company.bookstore.repository;
import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
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
    private Book book;
    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();

        book = new Book();

        book.setIsbn("9783161484100");
        book.setPublishDate("2015-08-01");
        book.setAuthorId(1);
        book.setTitle("An interesting book");
        book.setPublisherId(1);
        book.setPrice(29.99);
        book = bookRepository.save(book);
    }

    @Test
    public void addBook() {

        Book book = new Book();

        book.setIsbn("22222222222");
        book.setPublishDate("1995-08-01");
        book.setAuthorId(2);
        book.setTitle("An interesting book2");
        book.setPublisherId(2);
        book.setPrice(19.99);
        bookRepository.save(book);
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
        List<Book> books = bookRepository.findByAuthorId(book.getAuthorId());
        assertTrue(books.contains(book));
    }

    @Test
    public void shouldGetAllBooks() {

        List<Book> books = bookRepository.findAll();
        assertEquals(books.size(), 1);
    }
}
