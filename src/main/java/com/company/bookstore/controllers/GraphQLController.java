package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import com.company.bookstore.service.AuthorServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class GraphQLController {
    @Autowired
    AuthorServiceLayer authorServiceLayer;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;

    GraphQLController(){}

    @MutationMapping
    public Book addBook(
            @Argument String isbn,
            @Argument String publishDate,
            @Argument int authorId,
            @Argument String title,
            @Argument int publisherId,
            @Argument double price) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
        Book newBook = new Book(isbn, publishDate, author, title, publisher, price);

        return bookRepository.save(newBook);
    }
    @QueryMapping
    public Book getBookById(@Argument int bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return book.get();
        } else {
            return null;
        }
    }

    @QueryMapping
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @MutationMapping
    public Book updateBook(
            @Argument int bookId,
            @Argument String isbn,
            @Argument String publishDate,
            @Argument int authorId,
            @Argument String title,
            @Argument int publisherId,
            @Argument double price) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setIsbn(isbn);
        book.setPublishDate(publishDate);
        book.setAuthor(author);
        book.setTitle(title);
        book.setPublisher(publisher);
        book.setPrice(price);

        return bookRepository.save(book);
    }

    @MutationMapping
    public void deleteBook(@Argument int bookId) {
        bookRepository.deleteById(bookId);
    }

    @QueryMapping
    public List<Book> getBooksByAuthorId(@Argument int authorId){
        List<Book> book = bookRepository.getBooksByAuthorAuthorId(authorId);
        if (book.isEmpty()) {
            return null;
        } else {
            return book;
        }
    }

    @QueryMapping
    public Author findAuthorById(@Argument String id){
        Optional<Author> author = authorRepository.findById(Integer.parseInt(id));
        return author.orElse(null);
    }

    @QueryMapping
    public List<Author> getAuthors(){
        return authorRepository.findAll();
    }

    @SchemaMapping
    public Author author(Book book){
        Optional<Author> author = authorRepository.findById(book.getAuthor().getAuthorId());
        return author.orElse(null);
    }

    @SchemaMapping
    public Publisher publisher(Book book){
        Optional<Publisher> publisher = publisherRepository.findById(book.getPublisher().getId());
        return publisher.orElse(null);
    }
}
