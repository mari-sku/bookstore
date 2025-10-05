package com.example.bookstore.web;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// http://localhost:8080/index
// http://localhost:8080/booklist
// http://localhost:8080/addbook

// http://localhost:8080/h2-console    // url: jdbc:h2:mem:testdb   username: sa

@Controller
public class BookController {

// repositorioiden alustus ja konstruktori-injektio (kaikki repot samaan injektioon!!)
private final BookRepository bookRepository;
private final CategoryRepository categoryRepository;
public BookController(BookRepository repository, CategoryRepository categoryRepository) {
        this.bookRepository = repository;
        this.categoryRepository = categoryRepository;
    }

//INDEX (etusivu)
    @GetMapping("/index")
    public String index() {
        return "index";
    }

//BOOKLIST sivu
    @GetMapping("/booklist")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist"; //booklist.html
    }

//DELETE kirjan poistaminen listasta
@PreAuthorize("hasRole('ADMIN')")       //ei onnistu poisto urlilla ellei ole admin!
@GetMapping("/deletebook/{id}")
public String deleteBook(@PathVariable("id") Long id, Model model) {
    bookRepository.deleteById(id);
    return "redirect:../booklist";
}

//ADD kirjan lisääminen listaan
@GetMapping("/addbook")
public String addBook(Model model) {
   model.addAttribute("book", new Book());
    model.addAttribute("categories", categoryRepository.findAll());
   return "addbook";
}
//SAVE tallenna kirja listaan
@PostMapping("/save/{id}")
    public String saveBook(@PathVariable("id") Long id, @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editbook";
        }
        
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPublicationYear(book.getPublicationYear());
            existingBook.setIsbn(book.getIsbn());
            existingBook.setPrice(book.getPrice());
            bookRepository.save(existingBook);
        }
        
        return "redirect:/booklist";
    }

//EDIT

@GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            model.addAttribute("book", book);
            return "editbook";
        } else {
            return "redirect:/booklist";
        }
    }

} 

