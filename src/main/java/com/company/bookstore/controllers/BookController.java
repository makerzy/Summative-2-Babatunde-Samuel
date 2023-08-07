package com.company.bookstore.controllers;

import com.company.bookstore.models.Book;
import com.company.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    /** add a new book */
    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook (@RequestBody Book book){
        return bookRepository.save(book);
    }

    /** get a book by its id */
    @GetMapping("/books/{bookId}")
    public Book getBookById(@PathVariable int bookId) {
        Optional<Book> returnVal = bookRepository.findById(bookId);
        if(returnVal.isPresent()){
            return returnVal.get();
        } else {
            return null;
        }
    }

    /** get all books*/
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /** update an existing book*/
    @PutMapping("/books/book")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestBody Book book){
        bookRepository.save(book);
    }

    /** delete an existing book */
    @DeleteMapping("/books/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int bookId){
        bookRepository.deleteById(bookId);
    }

    /** get a book by author id */
    @GetMapping("/books/authors/{authorId}")
    public List<Book> getBooksByAuthorId(@PathVariable int authorId) {
        return bookRepository.getBooksByAuthorAuthorId(authorId);
    }

}
