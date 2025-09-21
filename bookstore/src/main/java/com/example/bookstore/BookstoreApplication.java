package com.example.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@SpringBootApplication

//jos pitää tappaa prosessi:
// netstat -ano | findstr :8080
// taskkill /PID <PID> /F

// http://localhost:8080/h2-console    // url: jdbc:h2:mem:testdb   username: sa
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
// bean-annotaatio kertoo Springille, että metodia kutsutaan heti sovellusta käynnistäessä. näin oliota voi myös käyttää muissa komponenteissä dependency injection kautta
	@Bean
    // commandlinerunner on Springin käyttöliittymä, jonka metodi suoritetaan sovelluksen heti käynnistyessä. kätevä tapa tehdä alustavaa dataa tietokantaan.
    //bookrepository injektoidaan metodiin Springin kautta, että voi käyttää repository-oliota tallentamaan book-olioita tietokantaan
public CommandLineRunner seedBooks(BookRepository repository) {
    return (args) -> {
        repository.save(new Book("Kafka on the Shore", "Haruki Murakami", 2002, "123", 10.50));
        repository.save(new Book("A Game of Thrones", "George R. R. Martin", 1996, "456", 18.45));
        repository.save(new Book("The Bell Jar", "Sylvia Plath", 1963, "789", 10.99));
        repository.save(new Book("Harry Potter and the Sorcerer's Stone", "J. K. Rowling", 1997, "101112",0.01));
    };
}

@Bean
public CommandLineRunner seedCategories(CategoryRepository repository) {
    return (args) -> {
        repository.save(new Category("Fiction"));
        repository.save(new Category("Sci-fi"));
        repository.save(new Category("Biography"));
        repository.save(new Category("Mystery"));
        repository.save(new Category("Non-fiction"));
        repository.save(new Category("Fantasy"));
        repository.save(new Category("Computer Science"));
    };
}
}
