package com.example.bookshop.models;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Title;
    private String Description;
    private Integer Price;
    @ManyToOne(fetch = FetchType.EAGER)
    private Author author;
    private Integer numCopies;
    private Boolean available;
    @Enumerated(EnumType.STRING)
    private  Genre genre;
    private String Image;


    public Book(String title, String description, Integer price, Author author, Integer numCopies, Boolean available,Genre genre,String image) {
        Title = title;
        Description = description;
        Price = price;
        this.author = author;
        this.numCopies = numCopies;
        this.available = available;
        this.genre=genre;
        this.Image=image;

    }

    public Book() {
    }
}
