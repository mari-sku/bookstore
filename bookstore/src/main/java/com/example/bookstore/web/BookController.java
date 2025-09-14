package com.example.bookstore.web;
import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// http://localhost:8080/index
// http://localhost:8080/booklist
// http://localhost:8080/add
// http://localhost:8080/h2-console    // url: jdbc:h2:mem:testdb   username: sa

@Controller
public class BookController {

// repositorion alustus ja konstruktori-injektio
private final BookRepository repository;
public BookController(BookRepository repository) {
        this.repository = repository;
    }

//INDEX (etusivu)
    @GetMapping("/index")
    public String index() {
        return "index";
    }

//BOOKLIST sivu
    @GetMapping("/booklist")
    public String listBooks(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist"; //booklist.html
    }

//DELETE kirjan poistaminen listasta
@GetMapping("/delete/{id}")
public String deleteBook(@PathVariable("id") Long id, Model model) {
    repository.deleteById(id);
    return "redirect:../booklist";
}

//ADD kirjan lisääminen listaan
@GetMapping("/add")
public String addBook(Model model) {
   model.addAttribute("book", new Book());
   return "addbook";
}
//SAVE tallenna kirja listaan
@PostMapping("/save/{id}")
    public String saveBook(@PathVariable("id") Long id, @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editbook";
        }
        
        Book existingBook = repository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPublicationYear(book.getPublicationYear());
            existingBook.setIsbn(book.getIsbn());
            existingBook.setPrice(book.getPrice());
            repository.save(existingBook);
        }
        
        return "redirect:/booklist";
    }

//EDIT

@GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = repository.findById(id).orElse(null);
        if (book != null) {
            model.addAttribute("book", book);
            return "editbook";
        } else {
            return "redirect:/booklist";
        }
    }

} 

