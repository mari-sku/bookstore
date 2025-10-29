package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import java.util.Optional;

@SpringBootTest
public class BookRepositoryTests {

@Autowired
private BookRepository bookRepository;
@Autowired
private CategoryRepository categoryRepository;

 @Test

 // CREATE
    public void createNewBook() {
        Category category = new Category("Sci-Fi");
        categoryRepository.save(category);
        Book book = new Book("Testiotsikko", "Kirjailija", 1965, "978", 2.60, category);
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
        assertThat(book.getTitle()).isEqualTo("Testiotsikko");
    }

    // FIND
    @Test
    public void findBookByIdShouldReturnBook() {
        Category category = new Category("Fantasy");
        categoryRepository.save(category);
        Book savedBook = new Book("Testiotsikko", "Kirjailija", 2005, "2345", 12, category);
        bookRepository.save(savedBook);
        Optional<Book> foundBook = bookRepository.findById(savedBook.getId());  // optional, koska kirja saattaa tai ei saata löytyä = ei nullpointerexception
        assertThat(foundBook).isPresent();
        assertThat(foundBook.get().getTitle()).isEqualTo("Testiotsikko");
    }

    // DELETE
    @Test
    public void deleteBookShouldRemoveItFromRepository() {
        Category category = new Category("Fiction");
        categoryRepository.save(category);
        Book book = new Book("Testiotsikko", "Kirjailija", 1984, "234655", 10, category);
        bookRepository.save(book);
        Long id = book.getId();
        bookRepository.deleteById(id);
        Optional<Book> deletedBook = bookRepository.findById(id);    // optional, koska kirja saattaa tai ei saata löytyä = ei nullpointerexception
        assertThat(deletedBook).isEmpty();
    }}
