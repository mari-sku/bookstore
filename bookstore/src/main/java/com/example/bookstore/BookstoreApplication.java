package com.example.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
public CommandLineRunner demo(BookRepository repository) {
    return (args) -> {
        repository.save(new Book("Kafka on the Shore", "Haruki Murakami", 2002, "123", 10.50));
        repository.save(new Book("A Game of Thrones", "George R. R. Martin", 1996, "456", 18.45));
        repository.save(new Book("The Bell Jar", "Sylvia Plath", 1963, "789", 10.99));
        repository.save(new Book("Harry Potter and the Sorcerer's Stone", "J. K. Rowling", 1997, "101112",0.01));
    };
}
}
