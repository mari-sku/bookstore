package com.example.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//sisältää mm. login

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html
    }
}
