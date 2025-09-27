package com.example.bookstore.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Category {
// tekee id sarakkeen tauluun
    @Id
// generoi uniikin pääavaimen kaikille uusille entity olioille
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Long id;
    private String name;

    //tuodaan books-lista 
    // mappedBy="category" kertoo, että suhteen omistaa* Book-luokan category-attribuutti, eikä JPA luo erillistä join-taulua. 
    // (*suhteen omistava luokka on se, jonka taulussa on ulkoinen avain)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    //@JsonIgnore
	@JsonIgnoreProperties("category")  // one way to avoid infinite loop during JSON serialization/deserialization
     private List<Book> books;

   
// konstruktorit 
 public Category() {
    }

 public Category(Long id, String name) {
    this.id = id;
    this.name = name;
 }

 // kontruktori, joka ottaa vain kategorian nimen, sillä tietokanta jo luo uniikin id:n
public Category(String name) {
   this.name = name;
}

//getterit ja setterit categorylle

 public Long getId() {
   return id;
}

// public void setId(Long id) {     // ei välttämättä tarvita, ellei haluta manuaalisesti asettaa id:tä
//    this.id = id;
// }

public String getName() {
   return name;
}

public void setName(String name) {
   this.name = name;

   // getterit ja setterit book listille
   
}

 public List<Book> getBooks() {
   return books;
}

public void setBooks(List<Book> books) {
   this.books = books;
}

 // to string 
 @Override
 public String toString() {
    return "Category [id=" + id + ", name=" + name + "]";
 }
}
