package com.quarkusWithPanache.resource;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.quarkusWithPanache.model.Book;
import com.quarkusWithPanache.repository.BookRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Path("/books")
public class BookResource {

    @Inject
    BookRepository bookRepository;

    @Inject
    MongoClient mongoClient;

    private MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase("test").getCollection("books");
    }

    @Inject
    public BookResource(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GET
    public List<Book> getAllBooks() {
        return bookRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Book getBookById(@PathParam("id") String id) {
        return bookRepository.findById(new ObjectId(id));
    }

    @POST
    public Response addBook(Book book) {
        bookRepository.persist(book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") String id, Book book) {
        Book entity = bookRepository.findById(new ObjectId(id));
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entity.title = book.title;
        entity.author = book.author;
        entity.genre = book.genre;
        entity.year = book.year;
        bookRepository.update(entity);
        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") String id) {
        boolean deleted = bookRepository.deleteById(new ObjectId(id));
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
    @POST
    @Path("/bulk")
    public Response bulkAddBooks(List<Book> books) {
        // Prepare documents for bulk write
        List<Document> documents = new ArrayList<>();
        for (Book book : books) {
            documents.add(new Document("title", book.title)
                    .append("author", book.author)
                    .append("genre", book.genre)
                    .append("year", book.year));
        }

        // Use MongoClient to access the collection and perform bulk insert
        getCollection().insertMany(documents);

        return Response.status(Response.Status.CREATED).entity(books).build();
    }

    @GET
    @Path("/aggregate/genre-count")
    public Response countBooksByGenre() {

        List<Document> pipeline = new ArrayList<>();
        pipeline.add(new Document("$group", new Document("_id", "$genre")
                .append("count", new Document("$sum", 1))));

        List<Document> result = getCollection()
                .aggregate(pipeline)
                .into(new ArrayList<>());

        return Response.ok(result).build();
    }
}
