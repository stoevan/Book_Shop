package com.example.bookshop.models;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Name;
    private String Surname;
    private String image;
    @OneToMany(mappedBy ="author")
    private List<Book> books;

    public Author() {
    }

    public Author(String name, String surname,String image) {
        Name = name;
        Surname = surname;
        this.image=image;

    }


}
