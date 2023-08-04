package com.company.bookstore.repository;

import com.company.bookstore.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
