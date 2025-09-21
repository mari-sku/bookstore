package com.example.bookstore.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Category {
// tekee id sarakkeen tauluun
    @Id
// generoi uniikin pääavaimen kaikille uusille entity olioille
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Long id;
    private String name;
   
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

 // to string 
 @Override
 public String toString() {
    return "Category [id=" + id + ", name=" + name + "]";
 }
}
