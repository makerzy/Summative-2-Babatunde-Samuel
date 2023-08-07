package com.company.bookstore.controllers;

import com.company.bookstore.models.Author;
import com.company.bookstore.models.Publisher;
import com.company.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PublisherController {

    @Autowired
    PublisherRepository repo;

    //create publisher - POST
    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher addPublisher(@RequestBody Publisher publisher){return repo.save(publisher);}


    //read by id - GET by id
    @GetMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher getPublisherById(@PathVariable Integer id){
        Optional<Publisher> returnVal = repo.findById(id);
        return returnVal.orElse(null);
    }

    //read all - GET all
    @GetMapping("/publishers")
    @ResponseStatus(HttpStatus.OK)
    public List<Publisher> getPublishers(){return repo.findAll();}

    //update - PUT
    @PutMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher(@RequestBody Publisher publisher, @PathVariable int id){
        Optional<Publisher> publisher1 = repo.findById(id);
        if(publisher1.isPresent())
            repo.save(publisher);
//        else throw an error
    }

    //delete
    @DeleteMapping(value = "/publishers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable int id){repo.deleteById(id);}
}
