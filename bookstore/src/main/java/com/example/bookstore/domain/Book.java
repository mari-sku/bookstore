package com.example.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

    // id attribuutti tietokantaa varten
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    // KATEGORIA-TAULUN YHDISTÄMINEN BOOK-TAULUUN

    //suhde ManyToOne (kirjoja voi olla monia, mutta yhdellä kirjalla on yksi kategoria)
    @ManyToOne
    // @JsonIgnoreProperties - one way to avoid infinite loop during JSON serialization/deserialization with bidirectional relationships
    @JsonIgnoreProperties ("books") 
    //määritellään viiteavain Book-tauluun nimellä "category_id", jolla se yhdistetään Category-tauluun.
    @JoinColumn(name="category_id")
    // tuodaan category-olio
    private Category category;

    //attribuutit bookille
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
    private double price;

    //konstruktorit
    public Book() {
    }

    public Book(String title, String author, int publicationYear, String isbn, double price) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
    }

    public Book(String title, String author, int publicationYear, String isbn,
            double price, Category category) {
        
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
    return id;
}

    // getterit & setterit bookille
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // getterit ja setterit categorylle

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // to string bookille
    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + ", publicationYear=" + publicationYear + ", isbn=" + isbn
                + ", price=" + price + "]";
    }

    

}
