package com.company.bookstore.repository;

import com.company.bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthorId(int authorId);
    List<Book> findByPublisherId(int publisherId);

}
