package com.example.bookstore.web;
import com.example.bookstore.domain.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



// http://localhost:8080/index
// http://localhost:8080/booklist


@Controller
public class BookController {

    // repositorion alustus ja konstruktori-injektio
private final BookRepository repository;
public BookController(BookRepository repository) {
        this.repository = repository;
    }
    
//index (etusivu) controller
    @GetMapping("/index")
    public String index() {
        return "index";
    }

//booklist sivun controller
    @GetMapping("/booklist")
    public String listBooks(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist"; //booklist.html
    }
    
} 
