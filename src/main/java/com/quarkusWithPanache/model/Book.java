package com.quarkusWithPanache.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "books")
public class Book extends PanacheMongoEntity {
    public String title;
    public String author;
    public String genre;
    public int year;

    public Book() {
    }
}
