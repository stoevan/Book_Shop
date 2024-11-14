package com.example.bookshop.models.Dto;

import lombok.Data;

@Data
public class AuthorDto {
    private String Name;
    private String Surname;
    private String image;

    public AuthorDto() {
    }

    public AuthorDto(String name, String surname, String image) {
        Name = name;
        Surname = surname;
        this.image = image;
    }
}
