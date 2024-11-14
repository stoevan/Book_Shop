package com.example.bookshop.repository;

import com.example.bookshop.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bookshop.models.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> , JpaSpecificationExecutor {
    List<Book> findBookByAuthorId(Long authorId);


}
