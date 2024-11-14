package com.example.bookshop.models.Dto;


import com.example.bookshop.models.Genre;

import lombok.Data;

@Data
public class BookDto {
    private String Title;
    private String Description;
    private Integer Price;
    private Long  author;
    private Integer numCopies;
    private Boolean available;
    private Genre genre;
    private String Image;

    public BookDto() {
    }

    public BookDto(String title, String description, Integer price, Long author, Integer numCopies, Boolean available, Genre genre, String image) {
        Title = title;
        Description = description;
        Price = price;
        this.author = author;
        this.numCopies = numCopies;
        this.available = available;
        this.genre = genre;
        Image = image;
    }
}
