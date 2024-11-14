package com.example.bookshop.service;
import com.example.bookshop.models.Book;
import com.example.bookshop.models.Dto.BookDto;
import com.example.bookshop.models.Genre;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();
    Book findById(Long id);
    Optional<Book> FindById(Long id);
    Book save(String title, String  description, Integer price, Long authorId , Integer numCopies, Boolean available, Genre genre,String image);

    Optional<Book> save(BookDto  bookDto);
    Book edit(Long id,String title, String  description, Integer price, Long authorId , Integer numCopies, Boolean available, Genre genre,String image);

    Optional<Book> edit(Long id,BookDto bookDto);
    void delete(Long id);
    List<Book> filltered(String Title, Genre genre);
    List<Book> getBookbyAuthorId(Long authorId);

    Optional<Book> rent(Long id);
}
