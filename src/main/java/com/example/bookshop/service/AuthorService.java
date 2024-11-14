package com.example.bookshop.service;

import com.example.bookshop.models.Author;
import com.example.bookshop.models.Book;
import com.example.bookshop.models.Dto.AuthorDto;
import com.example.bookshop.models.Genre;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAll();
    Author findById(Long id);
    Optional<Author> FindById(Long id);
    Author save(String name, String surname,String image);
    Optional<Author> save(AuthorDto authorDto);
    Author edit(Long id,String name, String surname,String image);
    Optional<Author> edit(Long id,AuthorDto authorDto);
    void delete(Long id);
    List<Author> filltered(String Name);
}
