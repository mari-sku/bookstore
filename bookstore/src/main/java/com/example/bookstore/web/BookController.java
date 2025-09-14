package com.example.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// http://localhost:8080/index


@Controller

//index (etusivu) controller
public class BookController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }
} 
