package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.bookstore.domain.AppUser;
import com.example.bookstore.domain.AppUserRepository;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@SpringBootApplication

// jos pitää tappaa prosessi:
// netstat -ano | findstr :8080
// taskkill /PID <PID> /F

// http://localhost:8080/h2-console // url: jdbc:h2:mem:testdb username: sa

public class BookstoreApplication {
	// loggeri
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

@Bean // bean-annotaatio kertoo Springille, että metodia kutsutaan heti sovellustakäynnistäessä. näin oliota voi myös käyttää muissa komponenteissä dependency injection kautta
	// commandlinerunner on Springin käyttöliittymä, jonka metodi suoritetaan sovelluksen heti käynnistyessä. kätevä tapa tehdä alustavaa dataatietokantaan.
	// repositoriot injektoidaan metodiin Springin kautta, että voi käyttää repository-oliota tallentamaan book-olioita tietokantaan
	public CommandLineRunner seedData(BookRepository bookRepository, CategoryRepository categoryRepository, AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
		return (args) -> {
			log.info("save a couple of categories");
			// luodaan ja tallennetaan kategoriat
			Category fiction = categoryRepository.save(new Category("Fiction"));
			Category scifi = categoryRepository.save(new Category("Sci-fi"));
			Category biography = categoryRepository.save(new Category("Biography"));
			Category mystery = categoryRepository.save(new Category("Mystery"));
			Category nonfiction = categoryRepository.save(new Category("Sci-fi"));
			Category fantasy = categoryRepository.save(new Category("Fantasy"));
			Category compsci = categoryRepository.save(new Category("Computer Science"));

			log.info("save a couple of books");
			// luodaan ja tallennetaan kirjat kategorioineen
			bookRepository.save(new Book("Kafka on the Shore", "Haruki Murakami", 2002, "123", 10.50, fiction));
			bookRepository.save(new Book("A Game of Thrones", "George R. R. Martin", 1996, "456", 18.45, fantasy));
			bookRepository.save(new Book("The Bell Jar", "Sylvia Plath", 1963, "789", 10.99, fiction));
			bookRepository.save(new Book("Harry Potter and the Sorcerer's Stone", "J. K. Rowling", 1997, "101112", 12.99, fantasy));

			// tulostetaan loggerilla kaikki kirjat
			log.info("Fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

			// käyttäjät: user ja admin. salasanat hashattu 
		
			AppUser user1 = new AppUser("user", passwordEncoder.encode("user"), "USER");
			AppUser user2 = new AppUser("admin", passwordEncoder.encode("admin"), "ADMIN");
			appUserRepository.save(user1);
			appUserRepository.save(user2);
		};

	}
}
