package com.company.bookstore.controllers;

import com.company.bookstore.service.AuthorServiceLayer;
import com.company.bookstore.viewmodel.AuthorViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLController {
    @Autowired
    AuthorServiceLayer authorServiceLayer;

    @QueryMapping
    public AuthorViewModel getAuthorById(@Argument String id){
        return authorServiceLayer.getAuthorById(Integer.parseInt(id));
    }

    @QueryMapping
    public List<AuthorViewModel> getAuthors(){
        return authorServiceLayer.getAuthors();
    }
}
