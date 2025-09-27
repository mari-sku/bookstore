package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;

@CrossOrigin
@Controller
public class BookRestController {

    // tuodaan BookRepository
    private BookRepository bookRepository;

    // konstruktori-injektio
    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // RESTful service to get all books
    // Java-kielinen Book-luokan oliolista muunnetaan JSON-kirjalistaksi ja
    // lähetetään web-selaimelle vastauksena
    @GetMapping("/books")
    public @ResponseBody List<Book> getAllBooksRest() {
        return (List<Book>) bookRepository.findAll();
    }

    // RESTful service to get one book by id
    @GetMapping("/books/{id}")
    public @ResponseBody Optional<Book> getBookById(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId);
    }

}
