package com.company.bookstore.service;


import com.company.bookstore.models.Author;
import com.company.bookstore.models.Book;
import com.company.bookstore.repository.AuthorRepository;
import com.company.bookstore.repository.BookRepository;
import com.company.bookstore.repository.PublisherRepository;
import com.company.bookstore.viewmodel.AuthorViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorServiceLayer {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;


    @Autowired
    public AuthorServiceLayer(
            AuthorRepository authorRepository,
BookRepository bookRepository
    ){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private AuthorViewModel buildAuthorViewModel(Author author){
        AuthorViewModel authorViewModel = new AuthorViewModel();
        List<Book> books = this.bookRepository.findByAuthorId(author.getAuthorId());
        authorViewModel.setAuthorId(author.getAuthorId());
        authorViewModel.setFirstName(author.getFirstName());
        authorViewModel.setLastName(author.getLastName());
        authorViewModel.setEmail(author.getEmail());
        authorViewModel.setPhone(author.getPhone());
        authorViewModel.setStreet(author.getStreet());
        authorViewModel.setCity(author.getCity());
        authorViewModel.setState(author.getState());
        authorViewModel.setPostalCode(author.getPostalCode());
        authorViewModel.setBooks(books);
        return authorViewModel;
    }



    public List<AuthorViewModel> getAuthors(){
        List<Author> authors = authorRepository.findAll();
        List<AuthorViewModel> authorViewModels = new ArrayList<>();

        for (Author author:authors             ) {
            AuthorViewModel authorViewModel = buildAuthorViewModel(author);
            authorViewModels.add(authorViewModel);
        }

        return authorViewModels;
    }

    public AuthorViewModel getAuthorById(int authorId){
        Optional<Author> author = authorRepository.findById(authorId);
        AuthorViewModel authorViewModel = new AuthorViewModel();
        if(author.isPresent()) {
            authorViewModel = buildAuthorViewModel(author.get());
        }
        return authorViewModel;
    }

}
