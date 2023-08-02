package com.company.bookstore.controllers;

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
    public Publisher getPublisherById(@PathVariable Integer id){
        Optional<Publisher> returnVal = repo.findById(id);
        if(returnVal.isPresent()){
            return returnVal.get();
        }else{
            return null;
        }
    }

    //read all - GET all
    @GetMapping("/publishers")
    public List<Publisher> getPublishers(){return repo.findAll();}

    //update - PUT
    @PutMapping("/publishers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisher(@RequestBody Publisher publisher){repo.save(publisher);}

    //delete
    @DeleteMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable Integer id){repo.deleteById(id);}
}
