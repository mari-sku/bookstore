package com.example.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;


//// http://localhost:8080/index
// http://localhost:8080/booklist
// http://localhost:8080/addbook
// http://localhost:8080/categorylist
// http://localhost:8080/addcategory

// http://localhost:8080/h2-console    // url: jdbc:h2:mem:testdb   username: sa
@Controller
public class CategoryController {

//konstruktori-injektio
private final CategoryRepository repository;
public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    // kategorialista
 @GetMapping("/categorylist")
    public String listCategories(Model model) {
        model.addAttribute("categories", repository.findAll());
        return "categorylist"; //categorylist.html
    }
// LISÄÄ uusi kategoria 
@GetMapping("/addcategory")
public String addCategory(Model model) {
    model.addAttribute("category", new Category());
    return "addcategory"; //addcategory.html
}
//TALLENNA lisätty kategoria
@PostMapping("/savecategory")
public String saveCategory(@ModelAttribute Category category) {
    repository.save(category);
    return "redirect:/categorylist";
}
}
