# Book API Application

This is a REST API for managing books in a collection, implemented with a MongoDB backend. The API supports the following operations: bulk insert, insert one, find all books, find a book by ID, delete a book, update a book, and aggregate book data.

## Endpoints

### 1. Bulk Write (Insert Multiple Books)

This endpoint allows you to insert multiple books into the database at once.

**Request:**
```bash
curl -X POST http://localhost:8080/books/bulk \
   -H "Content-Type: application/json" \
   -d '[
        {
            "title": "The Midnight Library",
            "author": "Matt Haig",
            "genre": "Fiction",
            "year": 2020
        },
        {
            "title": "Atomic Habits",
            "author": "James Clear",
            "genre": "Self-Help",
            "year": 2018
        },
        {
            "title": "Dune",
            "author": "Frank Herbert",
            "genre": "Science Fiction",
            "year": 1965
        },
        {
            "title": "The Silent Patient",
            "author": "Alex Michaelides",
            "genre": "Thriller",
            "year": 2019
        },
        {
            "title": "Educated",
            "author": "Tara Westover",
            "genre": "Memoir",
            "year": 2018
        },
        {
            "title": "Sapiens: A Brief History of Humankind",
            "author": "Yuval Noah Harari",
            "genre": "Non-Fiction",
            "year": 2011
        },
        {
            "title": "Where the Crawdads Sing",
            "author": "Delia Owens",
            "genre": "Mystery",
            "year": 2018
        },
        {
            "title": "Becoming",
            "author": "Michelle Obama",
            "genre": "Autobiography",
            "year": 2018
        },
        {
            "title": "The Alchemist",
            "author": "Paulo Coelho",
            "genre": "Philosophical Fiction",
            "year": 1988
        },
        {
            "title": "The Great Gatsby",
            "author": "F. Scott Fitzgerald",
            "genre": "Classic",
            "year": 1925
        }
    ]'
```

### 2. Insert One Book

This endpoint allows you to insert a single book into the database.

**Request:**
```bash
curl -X POST "http://localhost:8080/books" -H "Content-Type: application/json" -d '{                        
  "title": "Quarkus in Action",
  "author": "John Doe",
  "genre": "Programming",
  "year": 2023
}'
```

### 3. Find All Books

This endpoint returns all books in the collection.

**Request:**
```bash
curl -X GET "http://localhost:8080/books" | jq
```

### 4. Find One Book by ID

This endpoint returns a single book based on its unique ID.

**Request:**
```bash
curl -X GET "http://localhost:8080/books/672f873b421eaa0c3e4da49f" | jq
```

### 5. Delete a Book by ID

This endpoint deletes a single book from the collection based on its ID.

**Request:**
```bash
curl -X DELETE "http://localhost:8080/books/672f873b421eaa0c3e4da49f" | jq
```

### 6. Update a Book

This endpoint updates a book's information based on its ID.

**Request:**
```bash
curl -X PUT "http://localhost:8080/books/672f856f421eaa0c3e4da49e" \
   -H "Content-Type: application/json" \
   -d '{
         "title": "Quarkus in Action",
         "author": "John Doe",
         "genre": "Programming",
         "year": 2021
       }'
```

### 7. Aggregation - Genre Count

This endpoint performs an aggregation to count the number of books by genre.

**Request:**
```bash
curl -X GET "http://localhost:8080/books/aggregate/genre-count" | jq
```

---

## Technologies Used

- **Backend**: Java with REST API (using JAX-RS)
- **Database**: MongoDB
- **Data Format**: JSON
- **CLI Tool for JSON Parsing**: `jq`

## Requirements

- MongoDB instance running locally or remotely.
- Java 21 or higher.
- Maven or Gradle to build the project.

## Running the Application

1. Clone the repository:

   ```bash
   git clone <repository-url>
   cd <repository-name>
   ```

2. Build the project (if using Maven):

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

The application will be available on `http://localhost:8080`.

---

## Additional Notes

- You can use `jq` for pretty-printing JSON output in the terminal.
- The endpoints assume that a MongoDB collection named `books` is used. Ensure your MongoDB instance is running and accessible.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


### Key Points:
- **Endpoint Documentation**: Each REST API call is described with its HTTP method and body data, where applicable.
- **Example Requests**: The `curl` examples for each operation are formatted and easy to copy-paste.
- **Technologies Used**: A brief overview of the tech stack and tools used (MongoDB, Java, JAX-RS, `jq`).
