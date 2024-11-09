package com.quarkusWithPanache.repository;


import com.quarkusWithPanache.model.Book;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookRepository implements PanacheMongoRepository<Book> {
}
